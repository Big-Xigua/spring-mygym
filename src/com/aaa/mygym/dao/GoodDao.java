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
public interface GoodDao {
    /**
     * 分页查询所有的商品信息
     * @param pageNumber
     * @param pageSize
     * @param searchGoods
     * @param searchName
     * @return
     */
    List<Goods> getAllGoodsInfo(Integer pageNumber, Integer pageSize, String searchGoods, String searchName);

    /**
     * 查询所有商品信息总条数
     * @param searchGoods
     * @param searchName
     * @return
     */
    int getAllGoodsInfoCount(String searchGoods, String searchName);

    /**
     * 下架商品
     * @param goodsId
     * @param status
     * @return
     */
    int delGoods(Integer goodsId, Integer status);

    /**
     * 修改商品信息
     * @param goods
     * @return
     */
    int updateGoodsByGoodsId(Goods goods);

    /**
     * 获取所有单位
     * @return
     */
    List<Unit> getAllUnit();


    /**
     * 在商品列表显示类别
     */
    List<CateGory> GetGoodsCategory();

    /**
     * 增加商品
     * @param goods
     * @return
     */
    int addGoods(Goods goods);

    /**
     * 获取所有类别
     * @return
     */
    List<CateGory> getAllCategory(Integer pageNumber, Integer pageSize, String searchId, String searchName);

    /**
     *
     */
    int getAllCategoryCount(String searchId, String searchName);

    int updateCategory(CateGory cateGory);

    int addCategory(CateGory cateGory);


    /**
     * 获取最后一位商品号
     */
    String getLastGoodsId();

    /**
     * 商品消费里的商品列表
     * @param pageNumber
     * @param pageSize
     * @param searchGoods
     * @param searchName
     * @return
     */
    List<Goods> consumerGoodsList(Integer pageNumber, Integer pageSize, String searchGoods, String searchName);
}
