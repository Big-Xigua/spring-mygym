package com.aaa.mygym.dao;

import com.aaa.mygym.entity.Card;
import com.aaa.mygym.entity.CardType;
import com.aaa.mygym.entity.RechargeRecord;
import com.aaa.mygym.entity.RechargeRule;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date
 * 卡接口
**/ 
public interface CardDao {
    /**
     * 获得所有卡类型
     * @return
     */
    List<Card> getCardList();
    /**
     * 开卡
     * @param card
     * @return
     */
    int add(Card card);
    /**
     * 增加充值记录
     * @param rechargeRecord
     * @return
     */
    int addRechargeRecord(RechargeRecord rechargeRecord);
    /**
     * 获取最后一个卡信息
     * @return
     */
    Card getLastCard();
    /**
     * 根据Id去找卡
     * @return
     */
    Card findById(Integer id);

    /**
     * 分页查询所有会员卡信息
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     */
    List<Card> getAllCard(Integer pageNumber, Integer pageSize, String searchId, String searchName);
    /**
     * 查询所有会员卡信息条数
     * @param searchId
     * @param searchName
     * @return
     */
    int getAllCardInfoCount(String searchId, String searchName);
    /**
     * 禁用会员卡
     * @param cardId
     * @param status
     * @return
     */
    int delCard(Integer cardId, Integer status);
    /**
     * 充值
     * @param cardId
     * @param amount
     * @return
     */
    int rechargeCard(Integer cardId, double amount, Integer rechargeRule);
    /**
     * 会员激活
     * @param card
     * @return
     */
    int addCard(Card card);
    /**
     * 获取所有的充值规则
     * @return
     */
    List<RechargeRule> getRechargeRule();
    /**
     * 获取近一年的每个月的充值总额
     * @return
     */
    List<Map<String,Object>> getDataByNearYear();
    /**
     * 获取所有的卡信息
     * @return
     */
    List<Card> getAllCardInfo();
    /**
     * 获取所有的卡类型
     * @return
     */
    List<CardType> getAllCardTypeInfo();

    /**
     * 获得所有卡的消费信息
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     */
    List<RechargeRecord> getAllRechargeRecord(Integer pageNumber, Integer pageSize, String searchId, String searchName);

    /**
     * 获得所有卡的消费信息条数
     * @param searchId
     * @param searchName
     * @return
     */
    int getAllRechargeRecordCount(String searchId, String searchName);


}
