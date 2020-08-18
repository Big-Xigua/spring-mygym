package com.aaa.mygym.service.impl;


import com.aaa.mygym.dao.CardDao;
import com.aaa.mygym.dao.OrderDao;
import com.aaa.mygym.dao.impl.CardDaoImpl;
import com.aaa.mygym.dao.impl.OrderDaoImpl;
import com.aaa.mygym.entity.*;
import com.aaa.mygym.service.CardService;
import com.aaa.mygym.util.BusinessException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 获得所有卡信息
 */
public class CardServiceImpl implements CardService {
    private CardDao cardDao = new CardDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    /**
     * 分页查询所有会员卡信息
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> getAllCard(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws Exception {
        if (pageNumber == null || pageNumber == 0){
            throw new BusinessException("页数不能为空");
        }
        if (pageSize == null || pageSize == 0){
            throw new BusinessException("条数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        List<Card> list = cardDao.getAllCard(pageNumber,pageSize,searchId,searchName);
        int count = cardDao.getAllCardInfoCount(searchId,searchName);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("count",count);
        return map;
    }
    /**
     * 查询所有会员卡信息条数
     * @param searchId
     * @param searchName
     * @return
     */
    @Override
    public int getAllCardInfoCount(String searchId, String searchName) {
        return 0;
    }
    /**
     * 根据Id删除会员
     * @param cardId
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public int deluser(Integer cardId, Integer status) throws Exception {
        if (cardId == null) {
            throw new BusinessException("会员卡id不能为空");
        }
        return cardDao.delCard(cardId, status);
    }

    /**
     * 会员充值
     * @param cardId
     * @param amount
     * @param rechargeRule
     * @return
     * @throws Exception
     */
    @Override
    public int rechargeCard(Integer cardId, double amount,Integer rechargeRule) throws Exception{
        if (cardId == 0){
            throw new BusinessException("ID不能为空");
        }
        if (amount == 0){
            throw new BusinessException("余额不能为空");
        }
        return cardDao.rechargeCard(cardId,amount,rechargeRule);
    }

    /**
     * 新增会员
     * @param cardId
     * @param userId
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public int addCard(Integer cardId, Integer userId, Integer status) throws Exception {
        if (cardId ==null || cardId ==0){
            throw new BusinessException("会员编号不能为空");
        }
        if (userId ==null || userId ==0){
            throw new BusinessException("会员卡号不能为空");
        }
        if (status ==null || status ==0){
            throw new BusinessException("会员卡号不能为空");
        }
        Card card = new Card();
        card.setCardId(cardId);
        card.setUserId(userId);
        card.setStatus(status);
        int res = cardDao.addCard(card);
        return res;
    }
    /**
     * 获取所有的充值规则
     * @return
     */
    @Override
    public List<RechargeRule> getRechargeRule() {
        return cardDao.getRechargeRule();
    }
    /**
     * 获取近一年的每个月的充值总额
     * @return
     */
    @Override
    public List<Map<String, Object>> getDataByNearYear() {
        return cardDao.getDataByNearYear();
    }
    /**
     * 获取所有的卡类型
     * @return
     */
    @Override
    public List<CardType> getAllCardTypeInfo() {
        return cardDao.getAllCardTypeInfo();
    }
    /**
     * 开卡消费信息
     * @return
     */
    @Override
    public int createCard(Integer cardId, Integer cardType, double pay, String momo, Integer credit) throws Exception {
        Order order = new Order();
        order.setCardId(cardId+1);
        order.setCardType(cardType);
        order.setPay(pay);
        order.setMomo(momo);
        order.setCredit(credit);
        order.setCreatedTime(new Date());
        order.setStatus(1);
        order.setOrderId(Integer.parseInt(orderDao.getLastOrderId())+1);
        return orderDao.addOrder(order);
    }

    /**
     * 获取所有卡信息
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> getAllRechargeRecord(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws Exception {
        if (pageNumber == null || pageNumber == 0){
            throw new BusinessException("页数不能为空");
        }
        if (pageSize == null || pageSize == 0){
            throw new BusinessException("条数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        List<RechargeRecord> list = cardDao.getAllRechargeRecord(pageNumber,pageSize,searchId,searchName);
        int count = cardDao.getAllRechargeRecordCount(searchId,searchName);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("count",count);
        return map;
    }
}
