package com.aaa.mygym.dao.impl;

import com.aaa.mygym.dao.StaffDao;
import com.aaa.mygym.entity.Staff;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
public class StaffDaoImpl implements StaffDao {
    private BaseDao baseDao = new BaseDao();
    /**
     * 根据账户密码登录
    **/
    @Override
    public Map<String, Object> login(String staffId, String password) {
        String sql = "select * from staff where staffId = ? and password = ? and status = 1";
        Object[] params = {staffId, password};
        List<Map<String, Object>> lists = baseDao.executeQuery(sql, params);
        if (lists != null && lists.size() > 0) {
            return lists.get(0);
        }
        return null;
    }
    /**
     * 分页查询所有的员工信息
    **/
    @Override
    public List<Staff> getAllStaffInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName) {
        String sql = "select s.*,r.roleName from staff s,role r where s.roleId = r.id ";
        if (StringUtils.isNotBlank(searchId)) {
            sql += " and staffId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and staffName like '" + searchName + "'";
        }
        sql += " limit ?,?";
        Object[] params = {pageNumber, pageSize};
        List<Staff> staff = baseDao.queryList(sql, params, Staff.class);
        return staff;
    }
    /**
     * 查询所有员工信息的总条数
    **/
    @Override
    public int getAllStaffInfoCount(String searchId, String searchName) {
        String sql = "select count(1) len from staff where 1 = 1";
        if (StringUtils.isNotBlank(searchId)) {
            sql += " and staffId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and staffName like '" + searchName + "'";
        }
        List<Map<String, Object>> maps = baseDao.executeQuery(sql, null);
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = maps.get(0);
            Integer res = Integer.parseInt(map.get("len") + "");
            return res;
        }
        return 0;
    }
    /**
     * 修改员工信息
    **/
    @Override
    public int updateStaff(Staff staff) {
        String sql = "update staff set staffName = ?,phone = ?,idCard = ?,address = ?,status = ?,roleId = ?,momo = ?  where staffId = ?";
        Object[] params = {staff.getStaffName(), staff.getPhone(), staff.getIdCard(), staff.getAddress(), staff.getStatus()
                , staff.getRoleId(), staff.getMomo(),staff.getStaffId()};
        return baseDao.executeUpdate(sql, params);
    }
    /**
     *删除员工信息
    **/
    @Override
    public int delStaff(Integer staffId, Integer status) {
        status = status == 1 ? 2 : 1;
        String sql="Update staff set status =? where staffId="+staffId;
        Object[] params={status};
        int len=baseDao.executeUpdate(sql,params);
        return len;
    }

    /**
     * 增加员工信息
     * @param staff
     * @return
     */
    @Override
    public int addStaff(Staff staff) {
        String sql="insert into staff (staffName,phone,idCard,address,status,roleId,momo,staffId,createdTime,password)"
                                 +"values(?,?,?,?,?,?,?,?,?,?)";
        Object[] params={staff.getStaffName(),staff.getPhone(),staff.getIdCard(),staff.getAddress(),staff.getStatus(),
        staff.getRoleId(),staff.getMomo(),staff.getStaffId(),staff.getCreatedTime(),staff.getPassword()};
        int len=baseDao.executeUpdate(sql,params);
        return len;
    }

    @Override
    public String getLastStaffId() {
        String sql = "select staffId from staff order by staffId desc limit 1";
        List<Map<String, Object>> maps = baseDao.executeQuery(sql, null);
        if (maps != null && maps.size() > 0) {
            String userId = maps.get(0).get("staffId") + "";
            return userId;
        }
        return null;
    }

    @Override
    public int updateStaffPass(Staff staff) {
        String sql = "update staff set password = ? where staffId = ?";
        Object[] params = {staff.getPassword(), staff.getStaffId()};
        return baseDao.executeUpdate(sql, params);
    }
}
