package com.aaa.mygym.service;


import com.aaa.mygym.entity.Staff;
import com.aaa.mygym.util.BusinessException;

import java.util.Map;

public interface StaffService {
    /**
     * 根据账户密码登录
     * @return
     * @throws
     */
    Map<String,Object> login(String staffId,String password) throws Exception;

    /**
     * 分页查询所有的员工信息
     * @param pageNumber
     * @param pageSize
     * @param searchStuno
     * @param searchName
     * @return
     * @throws Exception
     */
    Map<String,Object> getAllStaffInfo(Integer pageNumber, Integer pageSize,
                                       String searchStuno,String searchName)throws Exception;

    /**
     * 修改员工信息
     * @param staffId
     * @param staffName
     * @param phone
     * @param idCard
     * @param status
     * @param roleId
     * @param remake
     * @param address
     * @return
     * @throws BusinessException
     */
    int updateStaffByStaffId(Integer staffId, String staffName, String phone,
                             String idCard, Integer status, Integer roleId, String remake, String address) throws BusinessException;

    /**
     * 删除员工信息，逻辑删除，只是修改员工状态
     * @param staffId
     * @param status
     * @return
     * @throws Exception
     */
    int delStaff(Integer staffId,Integer status)throws Exception;

    /**
     * 修改员工密码
     * @param staffId
     * @param password
     * @return
     */
    int updateStaffPass(Integer staffId ,String password);
}
