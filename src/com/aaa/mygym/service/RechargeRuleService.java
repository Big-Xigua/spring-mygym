package com.aaa.mygym.service;


import com.aaa.mygym.entity.RechargeRule;

import java.util.List;
import java.util.Map;

/**
 * 充值规则
 */
public interface RechargeRuleService {
    /**
     * 分页查询 所有的充值规则
     * @param pageNumber
     * @param pageSize
     * @param searchStartTime
     * @param searchEndTime
     * @param searchName
     * @param searchStatus
     * @return
     * @throws Exception
     */
   Map<String,Object> getRechargeRuleList(Integer pageNumber,
                           Integer pageSize,
                           String searchStartTime,
                           String searchEndTime,
                           String searchName,
                           Integer searchStatus)throws Exception;
   /**
    * 修改充值规则
    * @param id
    * @param ruleId
    * @param ruleName
    * @param ruleCoff
    * @param ruleStatus
    * @param ruleCreatedDate
    * @param ruleEndDate
    * @param ruleAmount
    * @return
    */
   int updateRechargeRuleById(String id,String ruleId,String ruleName,String ruleCoff,String ruleStatus,
                              String ruleCreatedDate,String ruleEndDate,String ruleAmount) throws Exception;

   /**
    * 删除规则
    * @param id
    * @return
    */
   int delRechargeRule(Integer id);

   List<RechargeRule> rechargeRule();

   /**
    *添加充值记录
    */
   int rechargeRecode(Integer cardId,double recharge,Integer rechargeRule,Integer staffId,double amount);
}
