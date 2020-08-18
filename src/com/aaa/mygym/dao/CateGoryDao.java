package com.aaa.mygym.dao;


import com.aaa.mygym.entity.Goods;

import java.util.List;

public interface CateGoryDao {
    /**
     * 分页查询 商品分类
     */
    List<Goods> getAllCateGoryList(Integer pageNumber, Integer pageSize,
                                   String searchName, Integer searchStatus);
    /**
     *分页查询 所有的 商品总条数
     */
    int getAllCateGoryCount(String searchName, Integer searchStatus);

}
