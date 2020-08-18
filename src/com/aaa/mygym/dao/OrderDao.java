package com.aaa.mygym.dao;

import com.aaa.mygym.entity.Order;

import java.util.List;
import java.util.Map;

/** 消费订单接口
 * @author Administrator
 */
public interface OrderDao {
    /**
     *  查询订单  内容
     * @param pageNumber
     * @param pageSize
     * @param searchOderId
     * @param searchName
     * @return
     */
    List<Order> getAllOrderInfo(Integer pageNumber, Integer pageSize, String searchOderId, String searchName);

    /**
     * 分页查询 消费订单条数
     * @param searchOderId
     * @param searchName
     * @return
     */
    int getAllOrderInfoCount(String searchOderId, String searchName);

    /**
     * 修改订单信息
     * @param order
     * @return
     */
    int updateOrder(Order order);

    /**
     * 删除(伪删除)订单信息
     * @param orderId
     * @param status
     * @return
     */
    int delOrder(Integer orderId, Integer status);

    /**
     *新增订单
     * @param order
     * @return
     */
    int addOrder(Order order);

    /**
     * 获取订单编号（最新）
     * @return
     */
    String getLastOrderId();

    /**
     * 获取近一年每个月的充值金额
     * @return
     */
    List<Map<String,Object>> getDataByNearYear();



}
