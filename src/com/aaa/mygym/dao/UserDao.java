package com.aaa.mygym.dao;

import com.aaa.mygym.entity.CardType;
import com.aaa.mygym.entity.User;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date
 * 会员
**/ 
public interface UserDao {
    /**
     * 获取会员列表
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<User> getAllUserList(Integer pageNumber, Integer pageSize,Integer searchName,String searchId);
    /**
     * 获取会员列表的条数
     * @return
     */
    int getAllUserCount(String searchName,Integer searchId);
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
    List<User> checkOnlyUser(String phone);

    /**
     * 增加会员
     * @param user
     * @return
     */
    int addUser(User user );

    /**
     * 获取最后一个user
     * @return
     */
    User getLastUser();
    /**
     * 获取近一年注册会员
     * @return
     */
    List<Map<String,Object>> getDataByNearYear();
    /**
     * 修改会员
     * @param user
     * @return
     */
    int updateUser(User user);
    /**
     * 删除会员
     * @param userId
     * @param status
     * @return
     */
    int delUser(Integer userId, Integer status);
    /**
     * 根据id查询会员信息
     * @param userId
     * @return
     */
    List<Map<String,Object>> getCardInfoById(Integer userId);
    /**
     *获取最后一个会员的id
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
     * @param Status
     * @param staffId
     * @return
     */
    int createCard(Integer cardId,String userId,double amount,Integer credit,Integer Status,Integer staffId,Integer leavel);
    /**
     * 获取最后一个会员的id
     * 用来实现会员id自增
     * @return
     */
    String getLastUserId();
}
