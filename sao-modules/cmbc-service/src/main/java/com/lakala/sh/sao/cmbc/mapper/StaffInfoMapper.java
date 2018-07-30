package com.lakala.sh.sao.cmbc.mapper;

import com.lakala.sh.sao.common.vo.StaffInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mybatis + 二级缓存(mapper级别的)
 *
 */
@Mapper
public interface StaffInfoMapper {

    @Select("SELECT count(STAFF_ID) FROM sys_staff_info")
    int findAllCount();

//    @Select("SELECT * FROM sys_staff_info") List<StaffInfo> findAll();
    List<StaffInfo> getAllStaff();

    // 配置了函数的返回值将被加入缓存。同时在查询时，会先从缓存中获取，若不存在才再发起对数据库的访问
    @Select("FROM sys_staff_info WHERE STAFF_ID=#{staffId}") StaffInfo findByStaffId(@Param("staffId") String staffId);

    @Select("SELECT * FROM sys_staff_info WHERE STAFF_NAME=#{staffName}")
    StaffInfo findByStaffName(@Param("staffName")String staffName);

    int insertSelective(StaffInfo record);

    StaffInfo selectByPrimaryKey(String staffId);

    int deleteByPrimaryKey(String staffId);

    int updateByPrimaryKeySelective(StaffInfo record);

    int updateByPrimaryKeyWithBLOBs(StaffInfo record);

    int updateByPrimaryKey(StaffInfo record);

    int insert(StaffInfo record);

}