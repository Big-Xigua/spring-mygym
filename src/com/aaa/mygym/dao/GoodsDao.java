package com.aaa.mygym.dao;

import com.aaa.mygym.entity.CateGory;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.entity.Unit;

import java.util.List;

/**
 * @author
 * @date
 *
**/ 
public interface GoodsDao {
    /**
     * 获得所有商品信息
     */
    List<Goods> selAllGoods(Integer pageNumber, Integer pageSize,
                            String searchName, Integer searchStatus);
    /**
     *分页查询 所有的 商品总条数
     */
    int selAllGoodsCount(String searchName, Integer searchStatus);

    /**
     * 通过ID 修改商品列表
     * @return
     */
    int updategoodsById(Goods goods);
    /**
     * 删除商品
     * @param goodsId
     * @param goodsStatus
     * @return
     */
    int delGoods(Integer goodsId, Integer goodsStatus);

    /**
     * 增加商品
     * @param goods
     * @return
     */
    int addGoods(Goods goods);
    /**
     * 获取最后一位商品号
     */
    String getLastGoodsId();
    /**
     * 获取所有单位
     * @return
     */
    List<Unit> getAllUnit();
    /**
     * 获取所有商品类型
     */
    List<CateGory> GetGoodsCategory();



}
