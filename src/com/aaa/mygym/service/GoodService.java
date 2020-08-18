package com.aaa.mygym.service;

import com.aaa.mygym.entity.CateGory;
import com.aaa.mygym.entity.Unit;

import java.util.List;
import java.util.Map;

public interface GoodService {
    /**
     * 分页查询所有商品的信息
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Map<String,Object> getAllGoodsInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName)throws Exception;

    /**
     * 下架商品
     * @param goodsId
     * @param status
     * @return
     * @throws Exception
     */
    int delGoods(Integer goodsId, Integer status) throws Exception;

    /**
     * 通过商品编号修改商品Id
     * @param goodsId
     * @param name
     * @param code
     * @param type
     * @param unitId
     * @param price
     * @param categoryId
     * @return
     */
    int updateGoodsByGoodsId(String goodsId, String name, String code, Integer type, Integer unitId, double price, Integer categoryId) throws Exception ;

    /**
     * 获取所有单位
     * @return
     */
    List<Unit> getAllUnit();

    /**
     * 获取所有类别
     * @return
     */
    Map<String, Object> getAllCategory(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws Exception;
    /**
     * 在商品列表显示类别
     */
    List<CateGory> GetGoodsCategory();
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
    int addGoods(String goodsId, String name, String code, Integer type, Integer unitId, double price, Integer categoryId)throws Exception;

    /**
     * 修改或添加商品分类
     * @param id
     * @param name
     * @param momo
     * @param status
     * @return
     * @throws Exception
     */
    int updateOrAddCategory(Integer id, String name, String momo, Integer status)throws Exception;


    /**
     * 获取最后一位商品号
     * @return
     */
    int getLastGoodsId();

    /**
     * 商品销售里的商品列表
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     * @return
     * @throws Exception
     */
    Map<String,Object> consumerGoodList(Integer pageNumber, Integer pageSize, String searchId, String searchName)throws Exception;
}
