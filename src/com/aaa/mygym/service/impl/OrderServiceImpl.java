package com.aaa.mygym.service.impl;

import com.aaa.mygym.dao.OrderDao;
import com.aaa.mygym.dao.impl.OrderDaoImpl;
import com.aaa.mygym.entity.Order;
import com.aaa.mygym.service.OrderService;
import com.aaa.mygym.util.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    /**
     * 查询订单
     * @param pageNumber
     * @param pageSize
     * @param searchorderId
     * @param searchName
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> getAllOrderInfo(Integer pageNumber, Integer pageSize, String searchorderId, String searchName) throws Exception {
        if (pageNumber == null || pageNumber == 0) {
            throw new BusinessException("页数不能为空");
        }
        if (pageSize == null || pageSize == 0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        List<Order> list = orderDao.getAllOrderInfo(pageNumber, pageSize, searchorderId, searchName);
        int count = orderDao.getAllOrderInfoCount(searchorderId, searchName);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", count);
        return map;
    }

    /**
     * 修改订单
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
    @Override
    public int updateOrderByOrderId( Integer orderId, Integer cardId, Integer cardType, double price, double pay, Integer credit, Integer status, String momo) throws Exception {
        if (orderId == null) {
            throw new BusinessException("订单编号不能为空");
        }
        Order order = new Order();
        order.setCardId(cardId) ;
        order.setCardType(cardType);
        order.setPrice(price);
        order.setPay(pay) ;
        order.setCredit(credit) ;
        order.setStatus(status);
        order.setMomo(momo);
        int res=0;
        if (orderId == 0) {
              //  新增订单
           String id1= orderDao.getLastOrderId();
            if (StringUtils.isNotBlank(id1)) {
              order.setCreatedTime(new Date());
                order.setOrderId(Integer.parseInt(id1) + 1);
                res=orderDao.addOrder(order);
            }else {
                throw new BusinessException("添加失败");
            }
        }else {
            // 修改
            order.setOrderId(orderId);
            res=orderDao.updateOrder(order);
        }
        return res;
    }

    /**
     * 删除订单
     * @param orderId
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public int delOrder(Integer orderId, Integer status) throws Exception {
        if (orderId == null || orderId == 0) {
            throw new BusinessException("订单编号不能为空");
        }
        return orderDao.delOrder(orderId,status);
    }
    /**
     * 获取近一年每个月的充值金额
     * @return
     */
    @Override
    public List<Map<String, Object>> getDataByNearYear() {
        return orderDao.getDataByNearYear();
    }
}

