package com.aaa.mygym.service.impl;

import com.aaa.mygym.dao.StaffDao;
import com.aaa.mygym.dao.impl.StaffDaoImpl;
import com.aaa.mygym.entity.Staff;
import com.aaa.mygym.service.StaffService;
import com.aaa.mygym.util.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author
 * @date
 *
**/
public class StaffServiceImpl implements StaffService {
    private StaffDao staffDao = new StaffDaoImpl();

    /**
     * 根据账户密码登录
     * @param loginName
     * @param loginPwd
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> login(String loginName, String loginPwd) throws Exception {
        return staffDao.login(loginName,loginPwd);
    }

    /**
     * 分页查询所有的员工信息
     * @param pageNumber
     * @param pageSize
     * @param searchStuno
     * @param searchName
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> getAllStaffInfo(Integer pageNumber, Integer pageSize, String searchStuno, String searchName) throws Exception {
        if (pageNumber == null || pageNumber == 0) {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null || pageSize == 0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        List<Staff> list = staffDao.getAllStaffInfo(pageNumber, pageSize, searchStuno, searchName);
        int count = staffDao.getAllStaffInfoCount(searchStuno, searchName);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", count);
        return map;
    }

    @Override
    public int updateStaffByStaffId(Integer staffId, String staffName, String phone, String idCard, Integer status, Integer roleId, String remake, String address) throws BusinessException {
        if (staffId == null) {
            throw new BusinessException("员工id不能为空");
        }
        Staff staff = new Staff();
        staff.setStaffName(staffName);
        staff.setPhone(phone);
        staff.setIdCard(idCard);
        staff.setStatus(status);
        staff.setRoleId(roleId);
        staff.setMomo(remake);
        staff.setAddress(address);
        staff.setPassword("123123");
        int res = 0;
        if (staffId == 0) {
            //增加
            String id = staffDao.getLastStaffId();
            if (StringUtils.isNotBlank(id)) {
                staff.setCreatedTime(new Date());
                staff.setPassword("123123");
                staff.setStaffId(Integer.parseInt(id) + 1);
                res = staffDao.addStaff(staff);
            } else {
                throw new BusinessException("增加失败");
            }
        } else {
            //修改
            staff.setStaffId(staffId);
            res = staffDao.updateStaff(staff);
        }
        return res;
    }

    @Override
    public int delStaff(Integer staffId, Integer status) throws Exception {
        if (staffId == null || staffId == 0) {
            throw new BusinessException("员工id不能为空");
        }
        return staffDao.delStaff(staffId,status);
    }
    @Override
    public int updateStaffPass(Integer staffId, String password) {
        Staff staff = new Staff();
        staff.setPassword(password);
        staff.setStaffId(staffId);

        int len = staffDao.updateStaffPass(staff);
        return len;
    }
}
