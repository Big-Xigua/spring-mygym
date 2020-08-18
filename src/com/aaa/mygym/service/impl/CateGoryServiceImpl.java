package com.aaa.mygym.service.impl;

import com.aaa.mygym.dao.CateGoryDao;
import com.aaa.mygym.dao.impl.CateGoryDaoImpl;
import com.aaa.mygym.entity.CateGory;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.service.CateGoryService;
import com.aaa.mygym.util.BusinessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CateGoryServiceImpl implements CateGoryService {
   private   CateGoryDao cateGoryDao=new CateGoryDaoImpl();
    @Override
    public Map<String, Object> getAllCateGoryList(Integer pageNumber, Integer pageSize, String searchName, Integer searchStatus)throws Exception {
        //参数校验
        if (pageNumber == null) {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null || pageSize == 0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        //所有的数据
        List<Goods> cateGoryList = cateGoryDao.getAllCateGoryList(pageNumber, pageSize, searchName, searchStatus);
        //总条数
        int count = cateGoryDao.getAllCateGoryCount(searchName, searchStatus);
        //我们现在要在同一时间把 所有数据+总条数 同时返回  所以需要用到 map
        Map<String, Object> map = new HashMap<>();
        map.put("list", cateGoryList);
        map.put("count", count);
        return map;
    }
}
