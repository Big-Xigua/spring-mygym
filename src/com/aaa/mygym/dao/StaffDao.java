package com.aaa.mygym.dao;

import com.aaa.mygym.entity.Staff;

import java.util.List;
import java.util.Map;

public interface StaffDao {
    /**
     * 根据账户密码登录
     * @param
     * @return
     */
    Map<String,Object> login(String staffId,String password);
    /**
     * 分页查询所有的员工信息
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     */
    List<Staff> getAllStaffInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName);

    /**
     * 查询所有员工信息的总条数
     * @param searchId
     * @param searchName
     * @return
     */
    int getAllStaffInfoCount(String searchId,String searchName);
    /**
     * 员工信息修改
     * @param staff
     * @return
     */
    int updateStaff(Staff staff);

    /**
     * 删除员工
     * @param staffId
     * @return
     */
    int delStaff(Integer staffId,Integer status);

    /**
     * 增加员工，员工入职
     * @param staff
     * @return
     */
    int addStaff(Staff staff);

    /**
     * 获取最后一个员工id
     * 用来实现员工id自增
     * @return
     */
    String getLastStaffId();

    /**
     * 修改密码
     * @param staff
     * @return
     */
    int updateStaffPass(Staff staff);
}
