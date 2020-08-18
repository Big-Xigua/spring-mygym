package com.aaa.mygym.service;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 消费订单
 */
public interface OrderService {
    /**
     * 查询订单
     * @param pageNumber
     * @param pageSize
     * @param searchOderId
     * @param searchName
     * @return
     * @throws Exception
     */
    Map<String,Object> getAllOrderInfo(Integer pageNumber, Integer pageSize, String searchOderId, String searchName)throws Exception;

    /**
     * 订单修改
     * @param orderId
     * @param cardId
     * @param cardType
     * @param price
     * @param pay
     * @param credit
     * @param status
     * @param momo
     * @return
     * @throws Exception
     */
    int updateOrderByOrderId(Integer orderId, Integer cardId, Integer cardType, double price, double pay, Integer credit, Integer status, String momo) throws Exception;

    /**
     * 订单审核（删除）
     * @param orderId
     * @param status
     * @return
     * @throws Exception
     */
    int delOrder(Integer orderId, Integer status) throws Exception;

    /**获取近一年的每个月的消费总额
     * @return
     */
    List<Map<String, Object>> getDataByNearYear();



}
