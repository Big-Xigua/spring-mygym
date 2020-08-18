package com.aaa.mygym.dao.impl;

import com.aaa.mygym.dao.GoodsDao;
import com.aaa.mygym.entity.CateGory;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.entity.Unit;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/** 
 * @author
 * @date
 * 
**/

public class GoodsDaoImpl implements GoodsDao {
     private BaseDao baseDao = new BaseDao();
    /**
     * 获得所有商品
     * @return
     */
    @Override
    public List selAllGoods(Integer pageNumber, Integer pageSize,
                            String searchName, Integer searchStatus) {
        String sql = "select g.*,ca.name categoryName,un.name unitName from goods g LEFT JOIN category ca ON g.categoryId=ca.id LEFT JOIN unit un on g.unitId=un.id " +
                "where 1 = 1";
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and g.name like '%" + searchName + "%'";
        }
        if (searchStatus != null && searchStatus != -1) {
            sql += " and type = " + searchStatus;
        }
        sql += " ORDER BY g.goodsId desc limit ?,? ";
        Object[] params = {pageNumber, pageSize};
        System.out.println(baseDao.queryList(sql, params, Goods.class));
        return baseDao.queryList(sql, params, Goods.class);
    }

    /**
     * 拿到商品的总条数
     * @return
     */
    @Override
    public int selAllGoodsCount( String searchName, Integer searchStatus) {
        String sql = "select count(1) len from goods where 1=1";
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and g.name like '%" + searchName + "%'";
        }
        if (searchStatus != null && searchStatus != -1) {
            sql += " and type = " + searchStatus;
        }
        List<Map<String, Object>> mapList = baseDao.executeQuery(sql, null);
        if (mapList != null && mapList.size() > 0) {
            return Integer.parseInt(mapList.get(0).get("len") + "");
        }
        return 0;
    }

    @Override
    public int updategoodsById(Goods goods) {

        String sql=" update goods set name = ?,code = ?,status = ?,type = ?,unitId = ?, price = ? where goodsId = ? ";
       Object[] params={goods.getName(),goods.getCode(),
               goods.getStatus(),goods.getType(),goods.getUnitId(),goods.getPrice(),goods.getGoodsId()};
        return baseDao.executeUpdate(sql, params);

    }

    @Override
    public int delGoods(Integer goodsId, Integer goodsStatus) {
        goodsStatus = goodsStatus == 1 ? 0 : 1;
        String sql = "update goods set status = ? where goodsId = " + goodsId;
        Object[] params = {goodsStatus};
        int len = baseDao.executeUpdate(sql, params);
        return len;
    }

    @Override
    public int addGoods(Goods goods) {
        String sql = "insert into goods(goodsId,name,code,type,unitId,price,categoryId) values (?,?,?,?,?,?,?)";
        Object[] params={goods.getGoodsId(),goods.getName(),goods.getCode(),goods.getType(),goods.getUnitId(),goods.getPrice(),goods.getCategoryId()};
        int len = baseDao.executeUpdate(sql,params);
        return len;
    }

    @Override
    public String getLastGoodsId() {
        String sql = "SELECT goodsId from goods order by goodsId desc LIMIT 1";
        List<Map<String,Object>> list = baseDao.executeQuery(sql,null);
        if (list != null && list.size() > 0) {
            String cardId = list.get(0).get("goodsId") + "";
            return cardId;
        }
        return null;
    }

    @Override
    public List<Unit> getAllUnit() {
        String sql = "select * from unit where status = 1";
        List<Unit> units = baseDao.executeQuery(sql,null);
        return units;
    }

    @Override
    public List<CateGory> GetGoodsCategory() {
        String sql = "select * from category where status = 1";
        List<CateGory> cateGoryList = baseDao.executeQuery(sql,null);
        return cateGoryList;

    }
}
