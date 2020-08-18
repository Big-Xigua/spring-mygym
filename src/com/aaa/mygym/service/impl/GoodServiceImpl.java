package com.aaa.mygym.service.impl;

import com.aaa.mygym.dao.GoodDao;
import com.aaa.mygym.dao.impl.GoodDaoImpl;
import com.aaa.mygym.entity.CateGory;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.entity.Unit;
import com.aaa.mygym.service.GoodService;
import com.aaa.mygym.util.BusinessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodServiceImpl implements GoodService {
    private GoodDao goodDao = new GoodDaoImpl();
    @Override
    public Map<String, Object> getAllGoodsInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws Exception {
        if (pageNumber == null || pageNumber == 0) {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null || pageSize == 0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        List<Goods> list = goodDao.getAllGoodsInfo(pageNumber, pageSize, searchId, searchName);
        int count = goodDao.getAllGoodsInfoCount(searchId, searchName);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", count);
        return map;
    }

    @Override
    public int delGoods(Integer goodsId, Integer status) throws Exception {
        if (goodsId == null || goodsId == 0) {
            throw new BusinessException("员工id不能为空");
        }
        return goodDao.delGoods(goodsId,status);
    }

    @Override
    public int updateGoodsByGoodsId(String goodsId, String name, String code, Integer type, Integer unitId, double price, Integer categoryId) throws Exception {
        if (goodsId == null || goodsId == "") {
            throw new BusinessException("商品编号不能为空");
        }
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setName(name);
        goods.setCode(code);
        goods.setCategoryId(categoryId);
        goods.setUnitId(unitId);
        goods.setPrice(price);
        goods.setType(type);
        return goodDao.updateGoodsByGoodsId(goods);
    }

    @Override
    public List<Unit> getAllUnit() {
        return goodDao.getAllUnit();
    }

    @Override
    public Map<String, Object> getAllCategory(Integer pageNumber,Integer pageSize,String searchId, String searchName) throws Exception {
        if (pageNumber == null || pageNumber == 0 )  {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null||pageSize==0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber=(pageNumber-1)*pageSize;

        List<CateGory> list= goodDao.getAllCategory(pageNumber,pageSize,searchId,searchName);
        int count=goodDao.getAllCategoryCount(searchId,searchName);
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("count",count);
        return map;
    }

    @Override
    public List<CateGory> GetGoodsCategory() {
        return goodDao.GetGoodsCategory();
    }

    @Override
    public int addGoods(String goodsId, String name, String code, Integer type, Integer unitId, double price,Integer categoryId)throws Exception {
        if (goodsId == null || goodsId == "") {
            throw new BusinessException("商品编号不能为空");
        }
        Goods good = new Goods();
        good.setGoodsId(goodsId);
        good.setName(name);
        good.setCode(code);
        good.setCategoryId(categoryId);
        good.setUnitId(unitId);
        good.setPrice(price);
        good.setType(type);
        return goodDao.addGoods(good);
    }

    @Override
    public int updateOrAddCategory(Integer id, String name, String momo, Integer status) throws Exception {
        CateGory cateGory = new CateGory();
        cateGory.setName(name);
        cateGory.setMomo(momo);
        cateGory.setStatus(status);
        int len = 0;
        if (id == null || id == 0) {
            len = goodDao.addCategory(cateGory);
        } else {
            cateGory.setId(id);
            len = goodDao.updateCategory(cateGory);
        }
        return len;
    }

    @Override
    public int getLastGoodsId() {
        return Integer.parseInt(goodDao.getLastGoodsId())+1;
    }

    @Override
    public Map<String, Object> consumerGoodList(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws Exception {
        if (pageNumber == null || pageNumber == 0) {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null || pageSize == 0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        List<Goods> list = goodDao.consumerGoodsList(pageNumber, pageSize, searchId, searchName);
        int count = goodDao.getAllGoodsInfoCount(searchId, searchName);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", count);
        return map;
    }
}
