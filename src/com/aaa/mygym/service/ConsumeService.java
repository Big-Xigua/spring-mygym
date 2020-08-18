package com.aaa.mygym.service;

import java.util.Map;

/**
 * @author
 * @data
 * @describe
 */
public interface ConsumeService {
    /**
     * 新增商品消费
     * @param orderId
     * @param cardId
     * @param price
     * @param pay
     * @param momo
     * @return
     */
    /**
     * 提交订单，更新商品数量，卡钱，增加订单
     */
    Map<String,Object>insertOrder(String ruleOrderId, String ruleCardId, String rulePrice, String rulePay, String ruleMOmo, String goodsId);
    /**
     * 商品列表
     */
    Map<String,Object> consumeGoodList(Integer pageNumber, Integer pageSize, String searchId, String searchName)throws Exception;

}
