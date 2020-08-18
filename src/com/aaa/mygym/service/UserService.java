package com.aaa.mygym.service;

import com.aaa.mygym.entity.CardType;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date
 * 获取用户列表
**/ 
public interface UserService {
    /**
     * 获取用户列表
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     * @throws Exception
     */
    Map<String,Object> getAllUserList(Integer pageNumber, Integer pageSize, Integer searchId, String searchName) throws Exception;
    /**
     * 查询所有的card 类型
     * @return
     */
    List<CardType> getCardTypeList();
    /**
     * 根据手机号 判断是否唯一
     * @param phone
     * @return
     */
    String checkOnlyUser(String phone) throws Exception;
    /**
     * 会员登记
     * @return
     * @throws Exception
     */
    int addUser(String userName,String userPhone,String userLevel,String userStatus,String staffName,
                String credit,String amount,String idno,String userSex,String province,String city,
                String address,String momo,String ruleId,String staffId,Integer cardId) throws Exception;
    /**
     * 获取近一年注册会员
     * @return
     */
    List<Map<String, Object>> getDataByNearYear();
    /**
     * 修改会员
     * @param userName
     * @param phone
     * @param status
     * @param address
     * @param idCard
     * @return
     */
    int updateUserById(String userName ,String phone,Integer status,String address,String idCard,Integer cardId,Integer userId) throws Exception;
    /**
     * 删除会员
     * 虽然 我们叫做删除 但是实际上只是修改状态不能删除
     * @param userId
     * @param status
     * @return
     * @throws Exception
     */
    int deluser(Integer userId, Integer status) throws Exception;
    /**
     * 根据id获得卡信息
     * @param userId
     * @return
     * @throws Exception
     */
    Map<String,Object> getCardInfoById(Integer userId)throws Exception;
    /**
     * 获取最后一个会员的id
     * 用来实现会员id自增
     * @return
     */
    String getLastCardId();
    /**
     * 在创建会员的时候办一张卡
     * @param cardId
     * @param userId
     * @param amount
     * @param credit
     * @param status
     * @param staffId
     * @param leavel
     * @return
     */
    int createCard(Integer cardId,String userId,double amount,Integer credit,Integer status,Integer staffId,Integer leavel) throws Exception;
    /**
     * 获取最后一个会员的id
     * 用来实现会员id自增
     * @return
     */
    String getLastUserId();
}
