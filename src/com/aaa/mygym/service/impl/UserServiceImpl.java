package com.aaa.mygym.service.impl;

import com.aaa.mygym.dao.CardDao;
import com.aaa.mygym.dao.UserDao;
import com.aaa.mygym.dao.impl.CardDaoImpl;
import com.aaa.mygym.dao.impl.UserDaoImpl;
import com.aaa.mygym.entity.Card;
import com.aaa.mygym.entity.CardType;
import com.aaa.mygym.entity.RechargeRecord;
import com.aaa.mygym.entity.User;
import com.aaa.mygym.service.UserService;
import com.aaa.mygym.util.BusinessException;
import com.aaa.mygym.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** 
 * @author
 * @date
 *
**/ 
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    CardDao cardDao = new CardDaoImpl();
    /**
     * 获取会员列表
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllUserList(Integer pageNumber, Integer pageSize,
                                              Integer searchId,String searchName) throws BusinessException {
        if(pageNumber == null){
            throw new BusinessException("页数不能为空");
        }
        if(pageSize == null){
            throw new BusinessException("列数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        List<User> userDaoList = userDao.getAllUserList(pageNumber, pageSize,searchId,searchName);
        int userDaoCount = userDao.getAllUserCount(searchName,searchId);
        Map<String, Object> map = new HashMap<>();
        map.put("list",userDaoList);
        map.put("count",userDaoCount);
        return map;
    }
    /**
     * 查询所有卡类型
     * @return
     */
    @Override
    public List<CardType> getCardTypeList() {
        return userDao.getCardTypeList();
    }
    /**
     * 根据手机号 判断是否唯一
     * @param phone
     * @return
     */
    @Override
    public String checkOnlyUser(String phone) throws Exception {
        if (StringUtils.isBlank(phone)) {
            throw new BusinessException("电话号码不能为空");
        }
        List<User> users = userDao.checkOnlyUser(phone);
        if (users != null && users.size() > 0) {
            return "该电话号码已经存在,请重新输入";
        }
        return null;
    }
    /**
     * 新增会员
     * @param userName
     * @param userPhone
     * @param userLevel
     * @param userStatus
     * @param staffName
     * @param credit
     * @param amount
     * @param idno
     * @param userSex
     * @param province
     * @param city
     * @param address
     * @param momo
     * @param ruleId
     * @param staffId
     * @param cardId
     * @return
     * @throws Exception
     */
    @Override
    public int addUser(String userName, String userPhone, String userLevel, String userStatus, String staffName, String credit,
                       String amount, String idno, String userSex, String province, String city, String address, String momo,
                       String ruleId, String staffId,Integer cardId) throws Exception {
        //参数校验
        /**
         * 会员登记
         *  1.user   //会员
         *  2.card    //卡
         *  3.rechargeRecord  充值记录
         */
        User user = new User();
        //获取最后一个对象
        User user1 = userDao.getLastUser();
        if (user1 == null) {
            throw new BusinessException("用户为空");
        }
        user.setUserId(user1.getUserId() + 1);
        user.setUserName(userName);
        user.setPhone(userPhone);
        user.setStatus(IntegerUtils.ToInteger(userStatus));
        user.setIdCard(idno);
        user.setCardId(cardId);
        user.setBirthday(idno.substring(6, 14));
        user.setSex(IntegerUtils.ToInteger(userSex));
        user.setAddress(address);
        user.setArea(province + city);
        user.setStaffId(IntegerUtils.ToInteger(staffId));
        user.setCreatedTime(new Date());
        user.setMomo(momo);
        int len1 = userDao.addUser(user);
        if (len1 <= 0) {
            throw new BusinessException("会员登记失败,请重新操作");
        }
        Card card = new Card();
        if (cardId == null) {
            throw new BusinessException("卡为空");
        }
        card.setCardId(cardId + 1);
        card.setUserId(user.getUserId());
        card.setAmount(Double.parseDouble(amount));
        card.setCredit(IntegerUtils.ToInteger(credit));
        card.setStatus(1);
        card.setStaffId(IntegerUtils.ToInteger(staffId));
        card.setLevel(IntegerUtils.ToInteger(userLevel));
        int len2 = cardDao.add(card);
        if (len2 <= 0) {
            throw new BusinessException("开卡失败,请重新操作");
        }
        card = cardDao.findById(card.getCardId());
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setcardId(card.getCardId());
        rechargeRecord.setrechargeAmount(Double.parseDouble(amount));
        rechargeRecord.setafterAmount(card.getAmount());
        rechargeRecord.setbeforeAmount(0.0);
        rechargeRecord.setruleId(IntegerUtils.ToInteger(ruleId));
        rechargeRecord.setstaffId(IntegerUtils.ToInteger(staffId));
        rechargeRecord.setcreatedTime(new Date());
        rechargeRecord.setMomo(staffName + "开卡,开卡时间" + new Date());
        int len3 = cardDao.addRechargeRecord(rechargeRecord);
        if (len3 <= 0) {
            throw new BusinessException("卡记录增加失败");
        }
        return 1;
    }
    /**
     * 获取近一年注册会员
     * @return
     */
    @Override
    public List<Map<String, Object>> getDataByNearYear() {
        return  userDao.getDataByNearYear();
    }

    /**
     * 修改会员
     * @param userName
     * @param phone
     * @param status
     * @param address
     * @param idCard
     * @return
     */
    @Override
    public int updateUserById(String userName, String phone, Integer status, String address, String idCard, Integer cardId, Integer userId)
            throws Exception {
        if (userId == 0) {
            throw new BusinessException("id不能为空");
        }
        User user = new User();
        user.setUserName(userName);
        user.setStatus(status);
        user.setPhone(phone);
        user.setIdCard(idCard);
        user.setAddress(address);
        user.setCardId(cardId);
        user.setUserId(userId);
        user.setCreatedTime(new Date());
        return userDao.updateUser(user);
    }
    /**
     * 删除会员
     * 虽然 我们叫做删除 但是实际上只是修改状态不能删除
     * @param userId
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public int deluser(Integer userId, Integer status) throws Exception {
        if (userId == null || userId == 0) {
            throw new BusinessException("会员id不能为空");
        }
        return userDao.delUser(userId, status);
    }
    /**
     * 根据id获得卡信息
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> getCardInfoById(Integer userId) throws Exception {
        List<Map<String, Object>> list = userDao.getCardInfoById(userId);
        if (list == null){
            Map<String,Object> maps = new HashMap<>(0);
            return  maps;
        }else {
            Map<String,Object> map = list.get(0);
            return map;
        }
    }
    /**
     * 获取最后一个会员的id
     * 用来实现会员id自增
     * @return
     */
    @Override
    public String getLastCardId() {
        return Integer.parseInt(userDao.getLastCardId())+"";
    }
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
    @Override
    public int createCard(Integer cardId, String userId, double amount, Integer credit, Integer status, Integer staffId, Integer leavel) throws Exception {
        return userDao.createCard(cardId+1,userId,amount,credit,status,staffId,leavel);
    }
    /**
     * 获取最后一个会员的id
     * 用来实现会员id自增
     * @return
     */
    @Override
    public String getLastUserId() {
        return userDao.getLastUserId();
    }
}
