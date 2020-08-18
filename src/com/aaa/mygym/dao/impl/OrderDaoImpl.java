package com.aaa.mygym.dao.impl;


import com.aaa.mygym.dao.OrderDao;
import com.aaa.mygym.entity.Order;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author
 */
public class OrderDaoImpl implements OrderDao {
private BaseDao baseDao= new BaseDao();

    /**
     * 查询订单
     * @param pageNumber
     * @param pageSize
     * @param searchOderId
     * @param searchName
     * @return
     */
    @Override
    public List<Order> getAllOrderInfo(Integer pageNumber, Integer pageSize, String searchOderId, String searchName) {
        String sql ="select o.*,c.`name`cardTypeName from `order` o ,cardType c where o.cardType=c.id ";
        if (StringUtils.isNotBlank(searchOderId) ) {
            sql +=" and orderId  = " + searchOderId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            sql +=" and cardId = " + searchName;
        }
        sql += "  ORDER BY orderId  DESC limit ?,? ";
       Object[] params={pageNumber,pageSize};
        List<Order> orders  =baseDao.queryList(sql,params, Order.class);
        return  orders;
    }

    /**
     * 订单列表
     * @param searchOderId
     * @param searchName
     * @return
     */
    @Override
    public int getAllOrderInfoCount(String searchOderId, String searchName) {
        String sql =" select count(1)  len from `order` where 1=1 ";
        if (StringUtils.isNotBlank(searchOderId) ) {
            sql +=" and orderId  = " + searchOderId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            sql +=" and cardId = " + searchName;
        }
        List<Map<String, Object>> maps = baseDao.executeQuery(sql,null);
        if (maps != null && maps.size()>0) {
            Map<String, Object> map = maps.get(0);
            Integer res =Integer.parseInt(map.get("len") +"");
            return  res;
        }
        return 0;
    }

    /**
     * 订单修改
     * @param order
     * @return
     */
    @Override
    public int updateOrder(Order order) {
        String sql = "update `order` set cardId = ?,cardType = ?,price = ?,pay = ?,credit = ?,status=?,momo=? where orderId = ?";
        Object[] params = {order.getCardId(),order.getCardType(),order.getPrice(),order.getPay(),order.getCredit(),order.getStatus(),order.getMomo(),order.getOrderId()};
        return baseDao.executeUpdate(sql, params);
    }

    /**
     * 订单删除
     * @param orderId
     * @param status
     * @return
     */
    @Override
    public int delOrder(Integer orderId, Integer status) {
        status= status== 0 ? 1 : 0;
        String sql= "update `order` set status = ? where orderId=" + orderId;
        Object[] params={status};
        int len =baseDao.executeUpdate(sql,params);
        return len;
    }

    /**
     * 新增订单
     * @param order
     * @return
     */
    @Override
    public int addOrder(Order order) {
        String sql = "insert into `order` (cardId,cardType,price,pay,credit,status,momo,orderId,createdTime)  values (?,?,?,?,?,?,?,?,?)";
        Object[] params = {order.getCardId(),order.getCardType(),order.getPrice(),order.getPay(),order.getCredit(),order.getStatus(),order.getMomo(),order.getOrderId(),order.getCreatedTime()};
        int res = baseDao.executeUpdate(sql,params);
        return res;
    }

    /**
     * 获取订单编号（最新）
     * @return
     */
    @Override
    public String getLastOrderId() {
        String sql = "select orderId from `order` order by id desc limit 1";
        List<Map<String, Object>> maps=baseDao.executeQuery(sql,null);
        if (maps != null && maps.size() > 0) {
            String orderId = maps.get(0).get("orderId") + "";
            return orderId;
        }
        return null;
    }

    /**
     * 获取近一年每个月的充值金额
     * @return
     */
    @Override
    public List<Map<String, Object>> getDataByNearYear() {
        String sql = "select SUM(pay) amount,SUBSTRING(createdTime FROM 6 FOR 2) month from `order` GROUP BY SUBSTRING(createdTime FROM 6 FOR 2)";
        return baseDao.executeQuery(sql,null);
    }
}
