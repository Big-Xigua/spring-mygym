package com.aaa.mygym.service;

import com.aaa.mygym.entity.CateGory;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.entity.Unit;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date
 *
**/ 
public interface GetGoodsService {
    /**
     * 获得所有商品信息
     * @return
     */
    Map<String,Object> selAllGoods(Integer pageNumber, Integer pageSize,
                                   String searchName, Integer searchStatus) throws Exception;

    /**
     * 修改商品列表
     * @param goodsCode
     * @param goodsName
     * @param goodsPrice
     * @param goodsType
     * @param goodsStatus
     * @return
     */
    int updategoodsById(String goodsId, String goodsCode, String goodsName,
                        Double goodsPrice, Integer goodsType, Integer unitId, Integer goodsStatus)throws Exception;

    /**
     * 删除商品
     * @param goodsId
     * @param goodsStatus
     * @return
     * @throws Exception
     */
    int delGoods(Integer goodsId, Integer goodsStatus) throws Exception;
    /**
     * 增加商品
     * @param goodsId
     * @param name
     * @param code
     * @param type
     * @param unitId
     * @param price
     * @return
     */
    int addGoods(String goodsId, String name, String code,
                 Integer type, Integer unitId, double price,
                 Integer categoryId)throws Exception;
    /**
     * 获取最后一位商品号
     * @return
     */
    int getLastGoodsId();
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
