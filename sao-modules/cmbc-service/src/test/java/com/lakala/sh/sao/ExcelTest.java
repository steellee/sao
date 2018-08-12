package com.lakala.sh.sao;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.lakala.sh.sao.vo.MsgClient;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * easypoi基于poi,快速完成Excel和word的各种操作
 * code: https://gitee.com/lemur/easypoi
 * doc: http://www.afterturn.cn/doc/easypoi.html
 *
 * 支持功能：
 *      基于注解的导入导出,修改注解就可以修改Excel
 *      支持常用的样式自定义
 *      基于map可以灵活定义的表头字段
 *      支持一堆多的导出,导入
 *      支持模板的导出,一些常见的标签,自定义标签
 *      支持HTML/Excel转换,如果模板还不能满足用户的变态需求,请用这个功能
 *      支持word的导出,支持图片,Excel
 *
 */
public class ExcelTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelTest.class);

    /**
     * excel大数据处理
     * 读取 test-65536.xls     OLE2NotOfficeXmlFileException
     * 读取xlsx ：
     *      5000     导入 2s   导出3s    写1s
     *      200000   导入 5s   导出7s    写4s
     *      1000000  导入 62s  导出32s   写17s
     */
    @Test
    public void test1() throws Exception {
        try {
            Date start = new Date();
            // ---------------------导入
            // 固定列定位
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            List<MsgClient> list = ExcelImportUtil.importExcelBySax(
                new FileInputStream(
                    new File(getWebRootPath("import/BigDataExport2000.xlsx"))),
                MsgClient.class, params);
            LOGGER.debug(">>>>>>>import end,time is {}", ((new Date().getTime() - start.getTime()) / 1000));
            // ---------------------导出
            Workbook workbook = ExcelExportUtil.exportBigExcel(new ExportParams("title_test", "sheet_test", ExcelType.XSSF),
                MsgClient.class, list);
            ExcelExportUtil.closeExportBigExcel();
            LOGGER.debug(">>>>>>>export end,time is {}", ((new Date().getTime() - start.getTime()) / 1000));

            // ---------------------写文件
            FileOutputStream fos = new FileOutputStream("D:/excel/BigDataExport2000-new.xlsx");
            workbook.write(fos);
            fos.close();
            LOGGER.debug(">>>>>>>write end,time is {}", ((new Date().getTime() - start.getTime()) / 1000));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * excel大数据处理
     * test-10000.xls       总时间 1s
     * test-65536.xls       导入 2s   导出2s    写1s
     * test-65536.xlsx      导入 20s  导出18s   写6s
     * test-1048576.xlsx   OutOfMemoryError
     */
    @Test
    public void test2() {
        try {
            Date start = new Date();
            LOGGER.debug("start");
            // ---------------------导入
            // 固定列定位
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            List<Map> list = ExcelImportUtil.importExcel(
                new File(getWebRootPath("import/test1000.xls")),
                Map.class, params);
            LOGGER.debug(">>>>>>>import end,time is {}", ((new Date().getTime() - start.getTime()) / 1000));

            // ---------------------导出
            // 写固定列
            List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
            entity.add(new ExcelExportEntity("商户号" , "商户号"));
            entity.add(new ExcelExportEntity("交易日期" , "交易日期"));
            entity.add(new ExcelExportEntity("交易时间" , "交易时间"));
            entity.add(new ExcelExportEntity("终端号" , "终端号"));
            entity.add(new ExcelExportEntity("交易类型" , "交易类型"));
            entity.add(new ExcelExportEntity("交易金额" , "交易金额"));
            entity.add(new ExcelExportEntity("清算金额" , "清算金额"));
            entity.add(new ExcelExportEntity("手续费" , "手续费"));
            entity.add(new ExcelExportEntity("流水号" , "流水号"));
            entity.add(new ExcelExportEntity("卡类型" , "卡类型"));
            entity.add(new ExcelExportEntity("支付方式" , "支付方式"));
            entity.add(new ExcelExportEntity("发卡行" , "发卡行"));
            entity.add(new ExcelExportEntity("唯一标识码" , "唯一标识码"));

            // .xls --> ExcelType.HSSF
            // .xlsx --> ExcelType.XSSF
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("title_test", "sheet_test", ExcelType.HSSF),
                entity, list);
            LOGGER.debug(">>>>>>>export end,time is {}", ((new Date().getTime() - start.getTime()) / 1000));

            // ---------------------写文件
            FileOutputStream fos = new FileOutputStream("D:/excel/test1000-new.xls");
            workbook.write(fos);
            fos.close();
            LOGGER.debug(">>>>>>>write file end,time is {}", ((new Date().getTime() - start.getTime()) / 1000));
        } catch (Exception e) {
        }
    }

    private static String getWebRootPath(String filePath) {
        try {
            String path = PoiPublicUtil.class.getClassLoader().getResource("").toURI().getPath();
            path = path.replace("WEB-INF/classes/", "");
            path = path.replace("file:/", "");
            return path + filePath;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
