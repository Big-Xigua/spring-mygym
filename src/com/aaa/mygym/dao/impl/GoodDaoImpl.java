package com.aaa.mygym.dao.impl;

import com.aaa.mygym.dao.GoodDao;
import com.aaa.mygym.entity.CateGory;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.entity.Unit;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class GoodDaoImpl implements GoodDao {
    private BaseDao baseDao= new BaseDao();
    @Override
    public List<Goods> getAllGoodsInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName) {
        String sql = "select " +
                " g.*,ca.name categoryName,un.name unitName " +
                " from " +
                " goods g LEFT JOIN category ca ON g.categoryId=ca.id LEFT JOIN unit un on g.unitId=un.id " +
                "where 1 = 1";
        if (StringUtils.isNotBlank(searchId)) {
            sql += " and g.goodsId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and g.name like '%" + searchName+"%'";
        }
        sql += " order by g.goodsId desc limit ?,?";
        Object[] params = {pageNumber, pageSize};
        List<Goods> list = baseDao.queryList(sql, params, Goods.class);
        return list;
    }

    @Override
    public int getAllGoodsInfoCount(String goodsId, String searchName) {
        String sql = "select count(1) len from goods where 1=1 and status=0";
        if (StringUtils.isNotBlank(goodsId)) {
            sql += " and goodsId = " + goodsId;
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

    @Override
    public int delGoods(Integer goodsId, Integer status) {
        status = status == 1 ? 2 : 1;
        String sql = "update goods set status = ? where goodsId = " + goodsId;
        Object[] params = {status};
        int len = baseDao.executeUpdate(sql, params);
        return len;
    }

    @Override
    public int updateGoodsByGoodsId(Goods goods) {
        String sql = "update goods set name = ?,code = ?,type = ?,price = ?,categoryId = ?,unitId = ? where goodsId = ?";
        Object[] params = {goods.getName(),goods.getCode(),goods.getType(),goods.getPrice(),goods.getCategoryId(),goods.getUnitId()
                ,goods.getGoodsId()};
        return baseDao.executeUpdate(sql, params);
    }

    @Override
    public List<Unit> getAllUnit(){
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


    @Override
    public int  addGoods(Goods goods) {
        String sql = "insert into goods(goodsId,name,code,type,unitId,price,categoryId) values (?,?,?,?,?,?,?)";
        Object[] params={goods.getGoodsId(),goods.getName(),goods.getCode(),goods.getType(),goods.getUnitId(),goods.getPrice(),goods.getCategoryId()};
        int len = baseDao.executeUpdate(sql,params);
        return len;

    }

    @Override
    public List<CateGory> getAllCategory(Integer pageNumber, Integer pageSize, String searchId, String searchName) {
        String sql="select * from " +
                " category" +
                " where 1=1 ";
        if (StringUtils.isNotBlank(searchId)) {
            searchId="%"+searchId+"%";
            sql+=" and id like '"+searchId+"'";
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName="'%"+searchName+"%'";
            sql+=" and name like "+searchName;
        }
        sql+=" order by id asc limit ?,? ";
        Object[] pas={pageNumber,pageSize};
        List<CateGory> list=baseDao.queryList(sql,pas,CateGory.class);
        return list;
    }

    @Override
    public int getAllCategoryCount(String searchId, String searchName) {
        String  sql="select count(1) len from " +
                "category " +
                " where 1=1 ";
        if (StringUtils.isNotBlank(searchId)) {
            searchId="%"+searchId+"%";
            sql+=" and  id like '"+searchId+"'";
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName="'%"+searchName+"%'";
            sql+=" and name like "+searchName;
        }
        List<Map<String,Object>> list=baseDao.executeQuery(sql,null);
        if (list != null && list.size()>0) {
            Map<String,Object> map=list.get(0);
            Integer len=Integer.parseInt(map.get("len")+"");
            return len;
        }

        return 0;
    }

    @Override
    public int updateCategory(CateGory cateGory) {
        String sql = "update category set name = ?,momo = ?,status = ? where id =? ";
        Object[] params = {cateGory.getName(),cateGory.getMomo(),cateGory.getStatus(),cateGory.getId()};
        int len = baseDao.executeUpdate(sql,params);
        return len;
    }

    @Override
    public int addCategory(CateGory cateGory) {
        String sql = "insert into category (name,momo,status) values (?,?,?)";
        Object[] params = {cateGory.getName(),cateGory.getMomo(),cateGory.getStatus()};
        return baseDao.executeUpdate(sql,params);
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
    public List<Goods> consumerGoodsList(Integer pageNumber, Integer pageSize, String searchGoods, String searchName) {
        String sql = "select " +
                " g.*,ca.name categoryName,un.name unitName " +
                " from " +
                " goods g LEFT JOIN category ca ON g.categoryId=ca.id LEFT JOIN unit un on g.unitId=un.id " +
                "where g.status = 0";
        if (StringUtils.isNotBlank(searchGoods)) {
            sql += " and g.goodsId = " + searchGoods;
        }
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and g.name like '%" + searchName+"%'";
        }
        sql += " order by g.goodsId desc limit ?,?";
        Object[] params = {pageNumber, pageSize};
        List<Goods> list = baseDao.queryList(sql, params, Goods.class);
        return list;
    }
}
