package com.aaa.mygym.service;




import java.util.Map;

public interface CateGoryService {
    /**
     * 分页查询 商品分类
     */
    Map<String,Object> getAllCateGoryList(Integer pageNumber, Integer pageSize,
                                          String searchName, Integer searchStatus) throws Exception;

}
