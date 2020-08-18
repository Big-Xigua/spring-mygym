package com.aaa.mygym.service;

import com.aaa.mygym.util.BusinessException;

import java.util.Map;

/**
 * @author
 * @date
 * 商品信息Service
**/ 
public interface GoodsService {
    /**
     * 获得所有商品信息
     * @param id
     * @param name
     * @param code
     * @param untiName
     * @param price
     * @param goodsTypeName
     * @return
     */
    Map<String,Object> selAllGoods(Integer pageNumber, Integer pageSize,
                                   String id, String name, String code, String untiName,
                                   String price, String goodsTypeName) throws Exception;
}
