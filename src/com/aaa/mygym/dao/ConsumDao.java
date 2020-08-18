package com.aaa.mygym.dao;
import com.aaa.mygym.entity.ConsumOrder;
import com.aaa.mygym.entity.Goods;

import java.util.List;

/**
 * @author
 * @data
 * @describe
 */
public interface ConsumDao {

    /**
     * 减少商品数量
     */
    int delGoodsNumber(Integer goodsId, Integer code);
    /**
     * 新增商品订单
     */
    int insertOrder(ConsumOrder consumOrder);
    /**
     * 扣费
     */
    int consumerCard(Integer cardId, double pay);

    /**
     * 商品列表
     */

    List<Goods> consumeGoodsList(Integer pageNumber, Integer pageSize, String searchGoods, String searchName);
    /**
     * 获取商品列表数目
     */

    int getAllGoodsInfoCount(String searchGoods, String searchName);
}