package com.lakala.sh.sao.cmbc.controller;

import com.lakala.sh.sao.common.constants.SysCode;
import com.lakala.sh.sao.common.fs.fastdfs.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo用
 * 应用场景：测试Fastdfs文件操作
 */
@Slf4j
@RestController
public class FileController {

    @Autowired
    private FastDFSClient fastDFSClient;

    /**
     * 上传文件
     * @param file 上传的文件
     * @return
     */
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public Map<String, Object> uploadFile(@RequestParam("uploadFile") MultipartFile file) {
        log.info("filedata:" + file.toString());
        Map<String, Object> m = new HashMap<String, Object>();

        if (file != null && !file.isEmpty()) {
            try {

                String fileId = fastDFSClient.uploadFile(file.getBytes(), file.getOriginalFilename());
                System.out.println("upload success. file id is: " + fileId);
                m.put("code", SysCode.SUCCESS.CODE);
                m.put("fileId", fileId);
                m.put("msg", "上传成功");

            } catch (Exception e) {
                e.printStackTrace();
                m.put("code", SysCode.SYSTEM_ERR.CODE);
                m.put("msg", "上传失败");
            }
        } else {
            m.put("code", SysCode.NO_SUPPORT_PARAM.CODE);
            m.put("msg", "参数丢失");
        }
        return m;
    }

    /**
     *  下载文件
     * @param response  HttpServletResponse
     * @param fileId    上传时返回的fileId
     * @param fileName  下载后生成的文件名
     */
    @RequestMapping(value = "downloadFile", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, String fileId, String fileName) {
        try {
            // 判断文件是否存在
            if (fastDFSClient.getFileInfo(fileId) != null) {
                byte[] result = fastDFSClient.downloadFile(fileId);
                System.out.println("download result is: " + result.length);
                // 清空response
                response.reset();
                // 设置response的Header
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                response.addHeader("Content-Length", "" + result.length);
                // 通过文件流的形式写到客户端
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(result);
                // 写完以后关闭文件流
                toClient.flush();
                toClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}