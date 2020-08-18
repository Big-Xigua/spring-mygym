package com.aaa.mygym.service.impl;

import com.aaa.mygym.dao.GoodsDao;
import com.aaa.mygym.dao.impl.GoodsDaoImpl;
import com.aaa.mygym.entity.CateGory;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.entity.Unit;
import com.aaa.mygym.service.GetGoodsService;
import com.aaa.mygym.util.BusinessException;
import com.aaa.mygym.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date
 *
**/
public class GetGoodsServiceImpl implements GetGoodsService {
    GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public Map<String, Object> selAllGoods(Integer pageNumber, Integer pageSize,
                                           String searchName, Integer searchStatus) throws Exception {
        if (pageNumber == null) {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null || pageSize == 0) {
            throw new BusinessException("每页条数不能为空");
        }

        pageNumber = (pageNumber - 1) * pageSize;

        List<Goods> goods = goodsDao.selAllGoods(pageNumber, pageSize,
                searchName,searchStatus);
        int i = goodsDao.selAllGoodsCount(searchName,searchStatus);
        Map<String, Object> map = new HashMap<>();
        map.put("list",goods);
        map.put("count",i);
        return map;
    }

    @Override
    public int updategoodsById(String goodsId, String goodsCode,String goodsName,
                               Double goodsPrice,Integer goodsType,Integer unitId,Integer goodsStatus)throws Exception {
        //参数效验

        if (goodsId == null || goodsId == "") {
            throw new BusinessException("商品编号不能为空");
        }
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setName(goodsName);
        goods.setCode(goodsCode);
        goods.setUnitId(unitId);
        goods.setPrice(goodsPrice);
        goods.setType(goodsType);
        goods.setStatus(goodsStatus);
        return goodsDao.updategoodsById(goods);
    }

    @Override
    public int delGoods(Integer goodsId, Integer goodsStatus) throws Exception {
        if (goodsId == null || goodsId == 0) {
            throw new BusinessException("员工id不能为空");
        }
        return goodsDao.delGoods(goodsId,goodsStatus);
    }

    @Override
    public int addGoods(String goodsId, String name, String code, Integer type, Integer unitId, double price, Integer categoryId) throws Exception {
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
        return goodsDao.addGoods(goods);
    }

    @Override
    public int getLastGoodsId() {
        return Integer.parseInt(goodsDao.getLastGoodsId())+1;
    }

    @Override
    public List<Unit> getAllUnit() {
        return goodsDao.getAllUnit();
    }

    @Override
    public List<CateGory> GetGoodsCategory() {
        return goodsDao.GetGoodsCategory();
    }
}
