package com.lakala.sh.sao.cmbc.service.impl;

import com.github.pagehelper.PageHelper;
//import com.lakala.sh.sao.common.config.db.DS;
import com.lakala.sh.sao.common.utils.IdUtil;
import com.lakala.sh.sao.common.vo.PageBean;
import com.lakala.sh.sao.common.vo.StaffInfo;
import com.lakala.sh.sao.cmbc.mapper.StaffInfoMapper;
import com.lakala.sh.sao.cmbc.service.StaffInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class StaffInfoServiceImpl implements StaffInfoService {

    @Autowired StaffInfoMapper staffInfoMapper;

//    @DS("localdbDS")
    @Override
    public StaffInfo findByStaffId(String id) {
//		StaffInfoVO staffInfoVO = staffInfoMapper.findByStaffName("admin");
//		StaffInfoVO staffInfoVO = staffInfoMapper.findByStaffId("admin");
        StaffInfo staffInfoVO = staffInfoMapper.selectByPrimaryKey(id);
        System.out.println("findByStaffId:"+ staffInfoVO.getEnglishName());
        return staffInfoVO;
    }

//    @DS("localdbDS")
    @Override
    // 方法级别事务控制：
    // 传播行为 REQUIRED 如果当前没有事务，
    // 隔离级别 READ_COMMITTED 创建一个新的事务 只能读取另一个事务已经提交的数据
//    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000, rollbackFor = Exception.class)
    public int insertSelective(StaffInfo record) {

        StaffInfo staffInfoVO = findByStaffId("admin");
        /*UUIDGenerator uuidGenerator = new UUIDGenerator();
        String uuid = IdUtil.getUUID();*/

        BigDecimal bigDecimal = IdUtil.getShortNanoTimeId(null);
        staffInfoVO.setStaffId(bigDecimal.toString());
        staffInfoVO.setStaffCode(bigDecimal.toString());

//        staffInfoMapper.insert(staffInfoVO);
//         bigDecimal = IdUtil.getShortNanoTimeId(null);
////         616b621d1f494e85a690b1ef0a36c0c6
//        staffInfoVO.setStaffId("616b621d1f494e85a690b1ef0a36c0c6");
//        staffInfoVO.setStaffCode(bigDecimal.toString());
        return staffInfoMapper.insertSelective(staffInfoVO);
    }

    public int findAllCount() {
        return staffInfoMapper.findAllCount();
    }

    public List<StaffInfo> findItemByPage(int currentPage,int pageSize) {
        // 设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        Date start = new Date();
        List<StaffInfo> allItems = staffInfoMapper.getAllStaff();    //全部员工
        log.info("end, time is {}: "+ (new Date().getTime() - start.getTime()) / 1000);
        int countNums = staffInfoMapper.findAllCount();              //总记录数
        PageBean<StaffInfo> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData.getItems();
    }
}
