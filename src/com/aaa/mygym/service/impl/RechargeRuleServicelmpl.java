package com.aaa.mygym.service.impl;

import com.aaa.mygym.dao.RechargeRuleDao;
import com.aaa.mygym.dao.impl.RechargeRuleImpl;
import com.aaa.mygym.entity.RechargeRecord;
import com.aaa.mygym.entity.RechargeRule;
import com.aaa.mygym.service.RechargeRuleService;
import com.aaa.mygym.util.BusinessException;
import com.aaa.mygym.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 充值规则
 */
public class RechargeRuleServicelmpl implements RechargeRuleService {
    private RechargeRuleDao rechargeRuleDao = new RechargeRuleImpl();
    @Override
    public Map<String, Object> getRechargeRuleList(Integer pageNumber, Integer pageSize, String searchStartTime, String searchEndTime, String searchName, Integer searchStatus) throws Exception {
        //参数校验
        if (pageNumber == null) {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null || pageSize == 0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        //所有的数据
        List<RechargeRule> rechargeRuleList = rechargeRuleDao.getRechargeRuleList(pageNumber,pageSize,searchStartTime,searchEndTime,searchName,searchStatus);
        //总条数
        int count = rechargeRuleDao.getRechargeRuleCount(searchStartTime,searchEndTime,searchName,searchStatus);
        //我们现在要在同一时间把 所有数据+总条数 同时返回  所以需要用到 map
        Map<String, Object> map = new HashMap<>();
        map.put("list", rechargeRuleList);
        map.put("count", count);
        return map;
    }

    @Override
    public int updateRechargeRuleById(String id, String ruleId, String ruleName, String ruleCoff, String ruleStatus, String ruleCreatedDate, String ruleEndDate, String ruleAmount) throws Exception {
        //参数校验
        if (StringUtils.isBlank(ruleName)) {
            throw new BusinessException("ruleName不能为空");
        }
        if (StringUtils.isBlank(ruleCoff)) {
            throw new BusinessException("ruleCoff不能为空");
        }
        if (StringUtils.isBlank(ruleStatus)) {
            throw new BusinessException("ruleStatus不能为空");
        }
        if (StringUtils.isBlank(ruleEndDate)) {
            throw new BusinessException("ruleEndDate不能为空");
        }
        if (StringUtils.isBlank(ruleAmount)) {
            throw new BusinessException("ruleAmount不能为空");
        }
        RechargeRule rechargeRule = new RechargeRule();
        rechargeRule.setId(IntegerUtils.ToInteger(ruleId));
        rechargeRule.setStatus(IntegerUtils.ToInteger(ruleStatus));
        rechargeRule.setCoefficient(Double.parseDouble(ruleCoff));
        rechargeRule.setName(ruleName);
        rechargeRule.setStartMoney(IntegerUtils.ToInteger(ruleAmount));
        rechargeRule.setEndTime(ruleEndDate);
        rechargeRule.setCreatedTime(ruleCreatedDate);
        int len = 0;
        if (StringUtils.isBlank(ruleId)) {
            //新增
            len = rechargeRuleDao.insertRechargeRule(rechargeRule);
        } else {
            //修改
            len =  rechargeRuleDao.updateRechargeRuleById(rechargeRule);
        }
        return len;
    }

    @Override
    public int delRechargeRule(Integer id) {
        return rechargeRuleDao.delRechargeRule(id);
    }

    @Override
    public List<RechargeRule>rechargeRule() {
        return rechargeRuleDao.RechargeRule();
    }

    @Override
    public int rechargeRecode(Integer cardId, double recharge, Integer rechargeRule, Integer staffId, double amount) {
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setcardId(cardId);
        rechargeRecord.setrechargeAmount(recharge);
        rechargeRecord.setruleId(rechargeRule);
        rechargeRecord.setbeforeAmount(amount);
        rechargeRecord.setafterAmount(rechargeRule+amount);
        rechargeRecord.setstaffId(staffId);
        rechargeRecord.setcreatedTime(new Date());
        return rechargeRuleDao.rechargeRecord(rechargeRecord);
    }
}
