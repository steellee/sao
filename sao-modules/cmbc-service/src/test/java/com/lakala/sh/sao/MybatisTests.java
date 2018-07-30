package com.lakala.sh.sao;

import com.lakala.sh.sao.cmbc.CmbcServiceApplication;
import com.lakala.sh.sao.cmbc.service.StaffInfoService;
import com.lakala.sh.sao.common.vo.StaffInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmbcServiceApplication.class)	// 指定spring-boot的启动类
//@SpringApplicationConfiguration(classes = Application.class)// 1.4.0 前版本
public class MybatisTests {

//	@Autowired
//	private StaffInfoMapper staffInfoMapper;

    @Autowired StaffInfoService staffInfoService;

    @Test
    public void findByStaffId() throws Exception{
//		StaffInfoVO staffInfoVO = staffInfoMapper.findByStaffName("admin");
//		StaffInfoVO staffInfoVO = staffInfoMapper.findByStaffId("admin");
//		StaffInfo staffInfoVO = staffInfoMapper.selectByPrimaryKey("admin");
        StaffInfo staffInfo = staffInfoService.findByStaffId("admin");
        Assert.assertEquals("admin", staffInfo.getStaffId());
    }

    @Test
    public void insertSelective(){
//		StaffInfoVO staffInfoVO = staffInfoMapper.findByStaffName("admin");
//		StaffInfoVO staffInfoVO = staffInfoMapper.findByStaffId("admin");
//		StaffInfo staffInfoVO = staffInfoMapper.selectByPrimaryKey("admin");

        int code = staffInfoService.insertSelective(null);
        Assert.assertEquals(1, code);
    }

    /**
     * 测试二级缓存
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        staffInfoService.findByStaffId("admin");
        staffInfoService.findByStaffId("admin");
    }
}
