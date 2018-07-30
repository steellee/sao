package com.lakala.sh.sao.cmbc.service;

import com.lakala.sh.sao.common.vo.StaffInfo;
import com.lakala.sh.sao.common.vo.StaffInfo;

import java.util.List;

public interface StaffInfoService {
    StaffInfo findByStaffId(String id);

    int insertSelective(StaffInfo record);

    int findAllCount();

    /**
     * 员工分页功能(集成mybatis的分页插件pageHelper实现)
     *
     * @param currentPage    :当前页数
     * @param pageSize        :每页显示的总记录数
     * @return
     */
    List<StaffInfo> findItemByPage(int currentPage,int pageSize);
}
