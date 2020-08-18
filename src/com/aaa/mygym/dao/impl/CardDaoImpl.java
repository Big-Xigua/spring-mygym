package com.aaa.mygym.dao.impl;

import com.aaa.mygym.dao.CardDao;
import com.aaa.mygym.entity.Card;
import com.aaa.mygym.entity.CardType;
import com.aaa.mygym.entity.RechargeRecord;
import com.aaa.mygym.entity.RechargeRule;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CardDaoImpl implements CardDao {
    private BaseDao baseDao = new BaseDao();
    /**
     * 获得所有卡类型
     * @return
     */
    @Override
    public List<Card> getCardList() {
        String sql = "select * from card";
        List<Card> mapList = baseDao.queryList(sql,null,Card.class);
        for (int i = 0; i < mapList.size(); i++) {
            String sql1 = "select name from cardtype where (select credit from card where cardId = ?) >= rank ORDER BY `level` desc limit 1";
            Object[] params = {mapList.get(i).getCardId()};
            List<Map<String, Object>> subMapList = baseDao.executeQuery(sql1,params);
            String cardType = "";
            if (subMapList != null && subMapList.size() > 0){
                cardType =  subMapList.get(0).get("name") + "";
            }
            mapList.get(i).setCardType(cardType);
            String sql2 = "select userName from user where userId = ?";
            Object[] params2 = {mapList.get(i).getUserId()};
            subMapList = baseDao.executeQuery(sql2,params2);
            String userName = "";
            if (subMapList != null && subMapList.size() > 0){
                userName =  subMapList.get(0).get("userName") + "";
            }
            mapList.get(i).setUserName(userName);
            System.out.println(mapList.get(i));
        }
        return null;
    }
    /**
     * 开卡
     * @param card
     * @return
     */
    @Override
    public int add(Card card) {
        String sql = "insert into card  (cardId,userId,amount,credit,status,staffId,levelId) values (?,?,?,?,?,?,?)";
        Object[] params = {card.getCardId(),card.getUserId(),card.getAmount(),card.getCredit(),card.getStatus(),card.getStaffId(),card.getLevel()};
        return baseDao.executeUpdate(sql,params);
    }
    /**
     * 增加充值记录
     * @param rechargeRecord
     * @return
     */
    @Override
    public int addRechargeRecord(RechargeRecord rechargeRecord) {
        String sql = "insert into rechargerecord  (cardId,rechargeAmount,afterAmount,beforeAmount,ruleId,createdTime,staffId,momo)" +
                " values (?,?,?,?,?,?,?,? )";
        Object[] params = {rechargeRecord.getcardId(),rechargeRecord.getrechargeAmount(),rechargeRecord.getafterAmount(),rechargeRecord.getbeforeAmount(),rechargeRecord.getruleId()
                ,rechargeRecord.getcreatedTime(),rechargeRecord.getstaffId(),rechargeRecord.getMomo()};
        return baseDao.executeUpdate(sql,params);
    }
    /**
     * 获取最后一个卡信息
     * @return
     */
    @Override
    public Card getLastCard() {
        String sql = "select * from card order by id desc limit 1";
        List<Card> cardList = baseDao.queryList(sql,null,Card.class);
        if (cardList != null && cardList.size() > 0){
            return cardList.get(0);
        }
        return null;
    }
    /**
     * 根据Id去找卡
     * @return
     */
    @Override
    public Card findById(Integer id) {
        String sql = "select * from card where cardId = ?";
        Object[] params = {id};
        List<Card> cardList = baseDao.queryList(sql,params,Card.class);
        if (cardList != null && cardList.size() > 0){
            return cardList.get(0);
        }
        return null;
    }
    /**
     * @author GHY
     * @date 2019/12/28
     * 新添加
    **/
    /**
     * 分页查询所有会员卡信息
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     */
    @Override
    public List<Card> getAllCard(Integer pageNumber, Integer pageSize, String searchId, String searchName) {
        String sql = "select u.userName userName,c.*,ct.name levelName from user u,card c,cardType ct where u.cardId = c.cardId and c.levelId = ct.`level`";
        if (StringUtils.isNotBlank(searchId)) {
            sql += " and c.cardId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and u.userName like '" + searchName + "'";
        }
        sql += " limit ?,?";
        Object[] params = {pageNumber, pageSize};
        List<Card> cards = baseDao.queryList(sql, params,Card.class);
        return cards;
    }
    /**
     * 查询所有会员卡信息条数
     * @param searchId
     * @param searchName
     * @return
     */
    @Override
    public int getAllCardInfoCount(String searchId, String searchName) {
        String sql = "select count(1) len from card  where 1=1";
        if (StringUtils.isNotBlank(searchId)) {
            sql += " and cardId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and u.userName like '" + searchName + "'";
        }
        List<Map<String, Object>> maps = baseDao.executeQuery(sql,null);
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = maps.get(0);
            Integer res = Integer.parseInt(map.get("len") + "");
            return res;
        }
        return 0;
    }
    /**
     * 禁用会员卡
     * @param cardId
     * @param status
     * @return
     */
    @Override
    public int delCard(Integer cardId, Integer status) {
        status = status == 1 ? 2 : 1;
        String sql = "update card set status = ? where cardId = " + cardId;
        Object[] params = {status};
        int len = baseDao.executeUpdate(sql, params);
        return len;
    }
    /**
     * 充值
     * @param cardId
     * @param amount
     * @return
     */
    @Override
    public int rechargeCard(Integer cardId, double amount ,Integer rechargeRule) {
        String sql = "select * from card  where cardId = ?";
        Object[] params ={cardId};
        List<Card> cardList = baseDao.queryList(sql,params,Card.class);
        if (cardList.size() != 0){
            if(rechargeRule != 0){
                String sql2 ="select * from rechargerule where status = 1 ";
                sql2+=" and id = ?";
                Object[] objects = {rechargeRule};
                List<RechargeRule> rechargeRuleList= baseDao.queryList(sql2,objects,RechargeRule.class);
                double oldAmount= cardList.get(0).getAmount();
                double newAmount = oldAmount + amount;
                String sql1 = "update card set amount = ? where cardId = ? ";
                Object[] params1 = {newAmount,cardId};
                return baseDao.executeUpdate(sql1,params1);
            }else{
                double newAmount = cardList.get(0).getAmount() + amount;
                String sql1 = "update card set amount = ? where cardId = ? ";
                Object[] params1 = {newAmount,cardId};
                return baseDao.executeUpdate(sql1,params1);
            }
        }
        return 0;
    }

    /**
     * 会员激活
     * @param card
     * @return
     */
    @Override
    public int addCard(Card card) {
        String sql = "insert into card (cardId,userId,status) values (?,?,?)";
        Object[] params = {card.getCardId(),card.getUserId(),card.getStatus()};
        int len = baseDao.executeUpdate(sql,params);
        return len;
    }
    /**
     * 获取所有的充值规则
     * @return
     */
    @Override
    public List<RechargeRule> getRechargeRule() {
        String sql = "select * from rechargerule where status = 1 and endTime >= now()";
        List<RechargeRule> list = baseDao.executeQuery(sql,null);
        return list;
    }
    /**
     * 获取近一年的每个月的充值总额
     * @return
     */
    @Override
    public List<Map<String, Object>> getDataByNearYear() {
        String sql = "select SUM(rechargeAmount) amount,SUBSTRING(createdTime FROM 6 FOR 2) month from rechargerecord GROUP BY SUBSTRING(createdTime FROM 6 FOR 2)";
        return baseDao.executeQuery(sql,null);
    }
    /**
     * 获取所有的卡信息
     * @return
     */
    @Override
    public List<Card> getAllCardInfo() {
        String sql = "select * from card";
        List<Card> cardList = baseDao.queryList(sql,null,Card.class);
        if (cardList != null){
            for (int i = 0; i < cardList.size(); i++) {
                Card card = cardList.get(i);
                sql = "select id,name from cardtype where rank < (select credit from card where cardId = ?) ORDER BY LEVEL desc limit 1";
                Object[] params = {card.getCardId()};
                List<Map<String,Object>> maps = baseDao.executeQuery(sql,params);
                if (maps != null && maps.size() > 0){
                    int cardTypeId = Integer.parseInt(maps.get(0).get("id") + "");
                    String cardTypeName = maps.get(0).get("name") + "";
                    card.setLevel(cardTypeId);
                    card.setLevelName(cardTypeName);
                }
            }
        }
        return cardList;
    }
    /**
     * 获取所有的卡类型
     * @return
     */
    @Override
    public List<CardType> getAllCardTypeInfo() {
        String sql = "select * from cardtype where status = 1";
        List<CardType> cardTypeList =  baseDao.queryList(sql,null,CardType.class);
        return cardTypeList;
    }

    /**
     * 获得所有充值信息
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     */
    @Override
    public List<RechargeRecord> getAllRechargeRecord(Integer pageNumber, Integer pageSize, String searchId, String searchName) {
        String sql = "select u.userName userName,re.* from rechargerecord re LEFT JOIN user u on re.cardId  = u.cardId where 1 = 1";
        if (StringUtils.isNotBlank(searchId)) {
            sql += " and re.cardId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and u.userName like '" + searchName + "'";
        }
        sql += " limit ?,?";
        Object[] params = {pageNumber, pageSize};
        List<RechargeRecord> rechargeRecords = baseDao.queryList(sql, params,RechargeRecord.class);
        return rechargeRecords;
    }

    @Override
    public int getAllRechargeRecordCount(String searchId, String searchName) {
        String sql = "select count(1) len from rechargerecord re where 1=1";
        if (StringUtils.isNotBlank(searchId)) {
            sql += " and re.cardId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and u.userName like '" + searchName + "'";
        }
        List<Map<String, Object>> maps = baseDao.executeQuery(sql,null);
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = maps.get(0);
            Integer res = Integer.parseInt(map.get("len") + "");
            return res;
        }
        return 0;
    }

}
