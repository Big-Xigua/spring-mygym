package com.aaa.mygym.dao.impl;

import com.aaa.mygym.dao.UserDao;
import com.aaa.mygym.entity.CardType;
import com.aaa.mygym.entity.User;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date
 * 会员Dao实现类
**/ 
public class UserDaoImpl implements UserDao{
    BaseDao baseDao = new BaseDao();
    /**
     * 获取用户列表
     * @return
     */
    @Override
    public List<User> getAllUserList(Integer pageNumber, Integer pageSize,Integer searchId,String searchName) {
//        String sql = "select u.*,c.amount amount,c.credit,c.staffId staffId, u.address address,ct.`name` from user u,card c,cardtype ct where u.cardId = c.cardId and c.levelId = ct.`level` ";
        String sql="SELECT user.id,user.userId,user.userName,user.phone,user.sex,user.address,user.idCard,user.status,user.cardId," +
                "card.amount,user.createdTime FROM user ,card WHERE user.cardId = card.cardId ";
        if (searchId!=0) {
            sql += " and user.userId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and userName like '" + searchName + "'";
        }
        sql += " group by user.id desc limit ?,?";
        Object[] params = {pageNumber, pageSize};
        List<User> user = baseDao.queryList(sql, params, User.class);
        return user;
    }

    /**
     * 获取会员列表的列数
     * @return
     */
    @Override
    public int getAllUserCount(String searchName,Integer searchId) {
        String sql = "select count(1) len from user where 1=1";
        if (searchId!=0 ){
            sql += " and userId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and userName like '" + searchName + "'";
        }
        List<Map<String, Object>> maps = baseDao.executeQuery(sql, null);
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = maps.get(0);
            Integer res = Integer.parseInt(map.get("len") + "");
            return res;
        }
        return 0;
    }
    /**
     * 查询所有的card 类型
     * @return
     */
    @Override
    public List<CardType> getCardTypeList() {
        String sql = "select * from cardtype where status = 1";
        List<CardType> cardTypeList = baseDao.queryList(sql,null,CardType.class);
        return cardTypeList;
    }
    /**
     * 根据手机号 判断是否唯一
     * @param phone
     * @return
     */
    @Override
    public List<User> checkOnlyUser(String phone) {
        String sql = "select * from user where phone is not null and phone = ?";
        Object[] params = {phone};
        return baseDao.queryList(sql,params,User.class);
    }
    /**
     * 新增会员
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {
        String sql = "insert into user  (userId,userName,phone,status,idCard,birthday,sex,address,area,createdTime,cardId,momo,staffId) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {user.getUserId(),user.getUserName(),user.getPhone(),user.getStatus(),user.getIdCard(),user.getBirthday(),user.getSex(),user.getAddress(),
                user.getArea(),user.getCreatedTime(),user.getCardId(),user.getMomo(),user.getStaffId()};
        return baseDao.executeUpdate(sql,params);
    }

    /**
     * 获取最后一个会员
     * @return
     */
    @Override
    public User getLastUser() {
        String sql = "select * from user order by id desc limit 1";
        List<User> mapList = baseDao.queryList(sql,null,User.class);
        if (mapList != null && mapList.size() > 0){
            return mapList.get(0);
        }
        return null;
    }
    /**
     * 获取近一年注册会员
     * @return
     */
    @Override
    public List<Map<String, Object>> getDataByNearYear() {
        String sql = "select COUNT(*) amount,SUBSTRING(createdTime FROM 6 FOR 2) month from `user` GROUP BY SUBSTRING(createdTime FROM 6 FOR 2)";
        return baseDao.executeQuery(sql,null);
    }
    /**
     * 修改会员
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        String sql = "update user set userName = ?,phone= ?,status= ?,address= ?,idCard= ?, cardId = ? ,createdTime = ? where userId = ?";
        Object[] params = {user.getUserName(), user.getPhone(), user.getStatus(), user.getAddress(), user.getIdCard(), user.getCardId(), user.getCreatedTime(), user.getUserId()};
        return baseDao.executeUpdate(sql, params);
    }
    /**
     * 逻辑删除会员，实为修改状态
     * @param userId
     * @param status
     * @return
     */
    @Override
    public int delUser(Integer userId, Integer status) {
        status = status == 1 ? 2 : 1;
        String sql = "update user set status = ? where userId = " + userId;
        Object[] params = {status};
        int len = baseDao.executeUpdate(sql, params);
        return len;
    }
    /**
     * 根据id查询会员信息
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> getCardInfoById(Integer userId) {
        String sql = "SELECT u.userId,userName,c.amount,c.cardId,c.levelId,c.credit,ct.`name` from user u LEFT JOIN card c on u.userId = c.userId LEFT JOIN cardtype ct on ct.`level` = c.levelId where  u.userId = ?";
        Object[] objects = {userId};
        List<Map<String,Object>> list = baseDao.executeQuery(sql,objects);
        if (list.size() > 0){
            return list;
        }else {
            return null;
        }
    }
    /**
     *获取最后一个会员的id
     * 用来实现会员id自增
     * @return
     */
    @Override
    public String getLastCardId() {
        String sql = "select cardId from user order by id desc limit 1";
        List<Map<String, Object>> maps = baseDao.executeQuery(sql, null);
        if (maps != null && maps.size() > 0) {
            String cardId = maps.get(0).get("cardId") + "";
            return cardId;
        }
        return null;
    }

    /**
     * 新增会员卡
     * @param cardId
     * @param userId
     * @param amount
     * @param credit
     * @param status
     * @param staffId
     * @param leavel
     * @return
     */
    @Override
    public int createCard(Integer cardId, String userId, double amount, Integer credit, Integer status, Integer staffId,Integer leavel) {
        String sql = "insert into card(cardId,userId,amount,credit,status,staffId,levelId) values (?,?,?,?,?,?,?) ";
        Object[] objects = {cardId,userId,amount,credit,status,staffId,leavel};
        int len = baseDao.executeUpdate(sql,objects);
        return len;

    }

    /**
     * 获取最后一个会员的id
     * @return
     */
    @Override
    public String getLastUserId() {
        String sql = "select userId from user order by id desc limit 1";
        List<Map<String, Object>> maps = baseDao.executeQuery(sql, null);
        if (maps != null && maps.size() > 0) {
            String userId = maps.get(0).get("userId") + "";
            return userId;
        }
        return null;
    }
}
