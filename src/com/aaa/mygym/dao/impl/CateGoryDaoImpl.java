package com.aaa.mygym.dao.impl;

import com.aaa.mygym.dao.CateGoryDao;
import com.aaa.mygym.entity.CateGory;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class CateGoryDaoImpl implements CateGoryDao {
    private BaseDao baseDao =new BaseDao();
    @Override
    public List<Goods> getAllCateGoryList(Integer pageNumber, Integer pageSize, String searchName, Integer searchStatus) {
        String sql = "select g.*,ca.name categoryName,un.name unitName from goods g LEFT JOIN category ca ON g.categoryId=ca.id LEFT JOIN unit un on g.unitId=un.id " +
                "where 1 = 1";
        /**
         * 如果 searchName 不为空 那么我们需要加上 此sql
         */
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and g.name like '%" + searchName + "%' ";
        }
        if (searchStatus != null && searchStatus != -1) {
            sql += " and g.status = " + searchStatus;
        }
        sql += " ORDER BY id desc limit ?,? ";
        Object[] params = {pageNumber, pageSize};

        return baseDao.queryList(sql, params, Goods.class);
    }

    @Override
    public int getAllCateGoryCount(String searchName, Integer searchStatus) {
        String sql = "select count(1) len goods where 1=1";
        /**
         * 如果 searchName 不为空 那么我们需要加上 此sql
         */
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and name like '%" + searchName + "%' ";
        }
        if (searchStatus != null && searchStatus != -1) {
            sql += " and status = " + searchStatus;
        }
        List<Map<String, Object>> mapList = baseDao.executeQuery(sql, null);
        if (mapList != null && mapList.size() > 0) {
            return Integer.parseInt(mapList.get(0).get("len") + "");
        }
        return 0;
    }
}
