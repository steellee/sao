package com.lakala.sh.sao;

import com.lakala.sh.sao.cmbc.CmbcServiceApplication;
import com.lakala.sh.sao.cmbc.controller.FileController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * Demo用
 * 应用场景：测试Fastdfs文件操作
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmbcServiceApplication.class)
public class FileTest {

    @Autowired
    private FileController fileController;

    @Test
    public void testploadFile() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("2.jpg", new FileInputStream(new File("D:/2.jpg")));
        fileController.uploadFile((MultipartFile)mockMultipartFile);
    }

    /**
     * 测试链接：
     * http://localhost:8501/sao/downloadFile?fileId=group1/M00/00/00/Cgdvs1tgRdCAQs3_AAKM8Z5mHHg6454501&fileName=test.jpg
     */
    @Test
    public void getFileByPath() {
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        fileController.downloadFile(mockHttpServletResponse, "group1/M00/00/00/Cgdvs1tgRdCAQs3_AAKM8Z5mHHg6454501", "test.jpg");
    }

    /*@Test
    public void testUploadFile() throws Exception{
        //1.向工程添加jar包
        //2.创建一个配置文件，配置tracker服务器地址
        //3.加载配置文件
        ClientGlobal.init("D:\\work\\ideaworkspace\\test\\sao\\github\\sao\\sao-modules\\cmbc-service\\src\\main\\resources\\fdfs_client.conf");
        //4.创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //5.使用TrackerClient对象获得trackerserver对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        //6.创建一个StorageServer的引用，我们用null就可以
        StorageServer storageServer = null;
        //7.创建一个StorageClient对象。trackerserver、StorageServer两个参数
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //8.使用StorageClient对象上传文件
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", "2");
        metaList[1] = new NameValuePair("createTime", "2017-04-13 16:01:00");
        metaList[2] = new NameValuePair("createUser", "zhangsan");
        String[] upload_files = storageClient.upload_file("D:/1.jpg", "jpg", metaList);
        for(String file : upload_files){
            System.out.println(file);
        }
    }*/
}
