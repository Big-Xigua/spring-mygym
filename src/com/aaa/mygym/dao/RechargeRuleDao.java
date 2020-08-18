package com.aaa.mygym.dao;

import com.aaa.mygym.entity.RechargeRecord;
import com.aaa.mygym.entity.RechargeRule;

import java.util.List;
/**
 * @author
 * @date
 * 充值规则
**/

public interface RechargeRuleDao {
    /**
     * 分页查询  充值规则
     * @param pageNumber
     * @param pageSize
     * @param searchStartTime
     * @param searchEndTime
     * @param searchName
     * @param searchStatus
     * @return
     */
    List<RechargeRule> getRechargeRuleList(Integer pageNumber,
                                           Integer pageSize,
                                           String searchStartTime,
                                           String searchEndTime,
                                           String searchName,
                                           Integer searchStatus);

    /**
     * 充值规则总条数
     * @param searchStartTime
     * @param searchEndTime
     * @param searchName
     * @param searchStatus
     * @return
     */
    int getRechargeRuleCount(String searchStartTime,
                             String searchEndTime,
                             String searchName,
                             Integer searchStatus);
    /**
     * 通过id 修改充值规则
     * @param rechargeRule
     * @return
     */
    int updateRechargeRuleById(RechargeRule rechargeRule);
    /**
     * 新增规则
     * @param rechargeRule
     * @return
     */
    int insertRechargeRule(RechargeRule rechargeRule);

    /**
     * 删除规则
     * @param id
     * @return
     */
    int delRechargeRule(Integer id);
    /**
     * 充值规则的名字
     */
    List<RechargeRule> RechargeRule();


    /**
     * 值时添加充值信息
     * @return
     */
    int rechargeRecord(RechargeRecord rechargeRecord);
}
