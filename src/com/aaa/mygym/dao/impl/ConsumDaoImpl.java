package com.aaa.mygym.dao.impl;

import com.aaa.mygym.dao.ConsumDao;
import com.aaa.mygym.entity.ConsumOrder;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @data
 * @describe
 */
public class ConsumDaoImpl implements ConsumDao {

    private BaseDao baseDao=new BaseDao();

    /**
     * 减少商品数量
     *减去购买商品，更新商品库存
     * @param code
     */
    @Override
    public int delGoodsNumber(Integer goodsId, Integer code) {
        String sql = "select code from goods where goodsId = ?";
        Object[] objects = {goodsId};
        List<Map<String,Object>> list = baseDao.executeQuery(sql,objects);
        Integer oldCode = Integer.parseInt(list.get(0).get("code")+"");
        String sql1 = "update goods set code = ? where goodsId = ?";
        Object[] obj= {oldCode-code,goodsId};
        int res = baseDao.executeUpdate(sql1,obj);
        return  res;
    }

    /**
     * 新增商品订单
     *
     * @param consumOrder
     */
    @Override
    public int insertOrder(ConsumOrder consumOrder) {
        String sql = "insert into `order` (orderId,cardId,cardType,price,pay,credit,status,momo,createdTime) values (?,?,?,?,?,?,?,?,?)";
        Object[] objects = {consumOrder.getOrderId(),consumOrder.getCardId(),consumOrder.getCardType(),consumOrder.getPrice(),consumOrder.getPay(),consumOrder.getCredit(),consumOrder.getStatus(),consumOrder.getMomo(),consumOrder.getCreatedtime()};
        int res  = baseDao.executeUpdate(sql,objects);
        System.out.println(res);
        return res;
    }

    /**
     * 扣费
     *查询卡余额减去消费，更新卡余额
     * @param cardId
     * @param pay
     */
    @Override
    public int consumerCard(Integer cardId, double pay) {
        String sql1 ="select amount from card where cardId = ?";
        Object[] objects1= {cardId};
        List<Map<String,Object>> list = baseDao.executeQuery(sql1,objects1);
        if (list!=null&&list.size()>0){

        double amount = Double.parseDouble(list.get(0).get("amount")+"");
        String sql = "update card set amount = ? where cardId = ?";
        Object[] objects= {amount-pay,cardId};
        int res = baseDao.executeUpdate(sql,objects);
        return res;
        }
        return 0;
    }

    /**
     * 商品列表
     *
     * @param pageNumber
     * @param pageSize
     * @param searchGoods
     * @param searchName
     */
    @Override
    public List<Goods> consumeGoodsList(Integer pageNumber, Integer pageSize, String searchGoods, String searchName) {

        String sql = "select " +
                " g.*,ca.name categoryName,un.name unitName " +
                " from " +
                " goods g LEFT JOIN category ca ON g.categoryId=ca.id LEFT JOIN unit un on g.unitId=un.id " +
                "where g.status = 1 and g.code>0 ";
        if (StringUtils.isNotBlank(searchGoods)) {
            sql += " and g.goodsId = " + searchGoods;
        }
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and g.name like '%" + searchName + "%'";
        }
        sql += " order by g.goodsId desc limit ?,?";
        Object[] params = {pageNumber, pageSize};
        List<Goods> list = baseDao.queryList(sql, params, Goods.class);
        return list;
    }
    /**
     * 获取商品列表数目
     */
    @Override
    public int getAllGoodsInfoCount(String searchGoods, String searchName) {
        String sql = "select count(1) len from goods where 1=1";
        if (StringUtils.isNotBlank(searchGoods)) {
            sql += " and goodsId = " + searchGoods;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and name like '" + searchName + "'";
        }
        List<Map<String, Object>> maps = baseDao.executeQuery(sql, null);
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = maps.get(0);
            Integer res = Integer.parseInt(map.get("len") + "");
            return res;
        }
        return 0;

    }

}
