package com.aaa.mygym.dao.impl;

import com.aaa.mygym.dao.RechargeRuleDao;
import com.aaa.mygym.entity.RechargeRecord;
import com.aaa.mygym.entity.RechargeRule;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 *
 *
 */
public class RechargeRuleImpl implements RechargeRuleDao {
    private BaseDao baseDao = new BaseDao();
    @Override
    public List<RechargeRule> getRechargeRuleList(Integer pageNumber, Integer pageSize, String searchStartTime, String searchEndTime, String searchName, Integer searchStatus) {
        String sql = "select * from rechargeRule where 1=1 ";
        /**
         * 如果 searchName 不为空 那么我们需要加上 此sql
         */
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and name like '%" + searchName + "%' ";
        }
        if (searchStatus != null && searchStatus != -1) {
            sql += " and status = " + searchStatus;
        }
        if (StringUtils.isNotBlank(searchStartTime) && StringUtils.isNotBlank(searchEndTime)) {
            sql += " and createdTime between '" + searchStartTime + "' and '" + searchEndTime + "'";
        }
        sql += " ORDER BY id desc limit ?,? ";
        Object[] params = {pageNumber, pageSize};

        return baseDao.queryList(sql, params, RechargeRule.class);
    }

    @Override
    public int getRechargeRuleCount(String searchStartTime, String searchEndTime, String searchName, Integer searchStatus) {
        String sql = "select count(1) len from rechargeRule where 1=1 ";
        /**
         * 如果 searchName 不为空 那么我们需要加上 此sql
         */
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and name like '%" + searchName + "%' ";
        }
        if (searchStatus != null && searchStatus != -1) {
            sql += " and status = " + searchStatus;
        }
        if (StringUtils.isNotBlank(searchStartTime) && StringUtils.isNotBlank(searchEndTime)) {
            sql += " and createdTime between '" + searchStartTime + "' and '" + searchEndTime + "'";
        }
        List<Map<String, Object>> mapList = baseDao.executeQuery(sql, null);
        if (mapList != null && mapList.size() > 0) {
            return Integer.parseInt(mapList.get(0).get("len") + "");
        }
        return 0;
    }

    @Override
    public int updateRechargeRuleById(RechargeRule rechargeRule) {
        String sql = "update rechargeRule set name = ?,coefficient = ?,status = ?,startMoney = ?,endTime = ? where id = ?";
        Object[] params = {rechargeRule.getName(), rechargeRule.getCoefficient(), rechargeRule.getStatus(),
                rechargeRule.getStartMoney(), rechargeRule.getEndTime(), rechargeRule.getId()};
        return baseDao.executeUpdate(sql, params);
    }

    @Override
    public int insertRechargeRule(RechargeRule rechargeRule) {
        String sql = "insert into rechargerule (name,coefficient,status,startMoney,endTime,createdTime) values" +
                "(?,?,?,?,?,?)";
        Object[] params = {rechargeRule.getName(), rechargeRule.getCoefficient(), rechargeRule.getStatus(),
                rechargeRule.getStartMoney(), rechargeRule.getEndTime(), rechargeRule.getCreatedTime()};
        return baseDao.executeUpdate(sql, params);
    }

    @Override
    public int delRechargeRule(Integer id) {
        String sql = "select status from rechargerule where id = ?";
        Object[] params = {id};
        List<Map<String, Object>> mapList = baseDao.executeQuery(sql, params);
        int status = 0;
        if (mapList != null && mapList.size() > 0) {
            status = Integer.parseInt(mapList.get(0).get("status") + "");
        }
        sql = "update rechargerule set status = ? where id = ?";
        status = status == 0 ? 1 : 0;
        Object[] params1 = {status,id};
        return baseDao.executeUpdate(sql,params1);
    }

    @Override
    public List<RechargeRule> RechargeRule() {
        String sql = "select * from rechargerule where status = 1";
        return baseDao.queryList(sql,null,RechargeRule.class);
    }

    @Override
    public int rechargeRecord(RechargeRecord rechargeRecord) {
        String  sql = "insert into  rechargerecord(cardId,rechargeAmount,afterAmount,beforeAmount,ruleId,createdTime,staffId,momo) values (?,?,?,?,?,?,?,?) ";
        Object[] objects = {rechargeRecord.getcardId(),rechargeRecord.getrechargeAmount()
                ,rechargeRecord.getafterAmount(),rechargeRecord.getbeforeAmount(),
                rechargeRecord.getruleId(),rechargeRecord.getcreatedTime(),
                rechargeRecord.getstaffId(),rechargeRecord.getMomo()};
        int  len = baseDao.executeUpdate(sql,objects);
        return len;
    }
}
