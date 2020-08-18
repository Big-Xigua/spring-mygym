package com.aaa.mygym.service;


import com.aaa.mygym.entity.CardType;
import com.aaa.mygym.entity.RechargeRule;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface CardService {
    /**
     * 分页查询所有会员卡信息
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     * @throws Exception
     */
    Map<String,Object> getAllCard(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws Exception;
    /**
     * 查询所有会员卡信息条数
     * @param searchId
     * @param searchName
     * @return
     */
    int getAllCardInfoCount(String searchId, String searchName)throws Exception;
    /**
     *删除会员卡
     * 虽然 我们叫做删除 但是实际上只是修改状态不能删除
     * @param cardId
     * @param status
     * @return
     * @throws Exception
     */
    int deluser(Integer cardId, Integer status) throws Exception;

    /**
     * 充值
     * @param cardId
     * @param recharge
     * @param rechargeRule
     * @return
     * @throws Exception
     */
    int rechargeCard(Integer cardId, double recharge, Integer rechargeRule)throws Exception;

    /**
     * 激活会员
     * @param cardId
     * @param userId
     * @param status
     * @return
     * @throws Exception
     */
    int addCard(Integer cardId, Integer userId, Integer status)throws Exception;
    /**
     * 获取所有的充值规则
     * @return
     */
    List<RechargeRule> getRechargeRule() throws Exception;
    /**
     * 获取近一年的每个月的充值总额
     *
     * @return
     */
    List<Map<String, Object>> getDataByNearYear();
    /**
     * 获取所有的卡类型
     * @return
     */
    List<CardType> getAllCardTypeInfo() throws Exception;
    /**
     * 开卡消费信息
     * @return
     */
    int createCard(Integer cardId, Integer cardType, double pay, String momo, Integer credit) throws Exception;
    /**
     * 获取所有卡的消费信息条数
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchCardName
     * @return
     * @throws Exception
     */
    Map<String,Object> getAllRechargeRecord(Integer pageNumber, Integer pageSize, String searchId, String searchCardName) throws Exception;
}
