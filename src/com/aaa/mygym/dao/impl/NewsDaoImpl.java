package com.aaa.mygym.dao.impl;

import com.aaa.mygym.dao.NewsDao;
import com.aaa.mygym.entity.News;
import com.aaa.mygym.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class NewsDaoImpl implements NewsDao {
        private BaseDao baseDao=new BaseDao();

    /**
     * 搜索
     * @param pageNumber
     * @param pageSize
     * @param searchTitle
     * @param searchName
     * @return
     */
    @Override
    public List<News> getAllNews(Integer pageNumber, Integer pageSize, String searchTitle, String searchName, String createdTime, String endTime) {
        String sql="select * from " +
                " (select n.*,s.staffName staffName  from news n,staff s  where  n.staffId=s.staffId) a " +
                " where 1=1 ";
        if (StringUtils.isNotBlank(searchTitle)) {
            searchTitle="%"+searchTitle+"%";
            sql+=" and title like '"+searchTitle+"'";
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName="'%"+searchName+"%'";
            sql+=" and staffName like "+searchName;
        }
        if(StringUtils.isNotBlank(createdTime)&& StringUtils.isNotBlank(endTime)){
            sql+=" and createdTime between '"+createdTime+"' and '"+endTime+"' and endTime<='"+endTime+"'";
        }else {
            if (StringUtils.isNotBlank(createdTime)) {
                sql += " and createdTime >'" + createdTime + "'";
            }
            if (StringUtils.isNotBlank(endTime)) {
                sql += " and  createdTime <'" + endTime + "'";
            }
        }
        sql+=" order by createdTime desc limit ?,? ";
        Object[] pas={pageNumber,pageSize};
        List<News> list=baseDao.queryList(sql,pas,News.class);
        return list;
    }

    /**
     * 获取总数
     * @param searchTitle
     * @param searchName
     * @return
     */
    @Override
    public int getAllNewsCount(String searchTitle, String searchName,String createdTime,String endTime) {
        String  sql="select count(1) len from " +
                "(select n.*,s.staffName staffName  from news n,staff s  where  n.staffId=s.staffId) a " +
                " where 1=1 ";
        if (StringUtils.isNotBlank(searchTitle)) {
            searchTitle="%"+searchTitle+"%";
            sql+=" and  title like '"+searchTitle+"'";
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName="'%"+searchName+"%'";
            sql+=" and staffName like "+searchName;
        }
        if(StringUtils.isNotBlank(createdTime)&& StringUtils.isNotBlank(endTime)){
            sql+=" and createdTime between '"+createdTime+"' and '"+endTime+"' and endTime<='"+endTime+"'";
        }else {
            if (StringUtils.isNotBlank(createdTime)) {
                sql += " and createdTime >'" + createdTime + "'";
            }
            if (StringUtils.isNotBlank(endTime)) {
                sql += " and  createdTime <'" + endTime + "'";
            }
        }
        List<Map<String,Object>> list=baseDao.executeQuery(sql,null);
        if (list != null && list.size()>0) {
            Map<String,Object> map=list.get(0);
            Integer len=Integer.parseInt(map.get("len")+"");
            return len;
        }

        return 0;
    }

    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int delNewsById(Integer id,Integer status) {
        status = status == 0 ? 1 : 0;
        String sql = "update  news set status = ? where id =" + id;
        Object[] pas = {status};

        return baseDao.executeUpdate(sql,pas);
    }

    /**
     * 添加新闻
     * @param news
     * @return
     */
    @Override
    public int addNews(News news) {
        String sql="insert into news (title,content,staffId,status,createdTime,endTime) values (?,?,?,?,?,?)";
        Object[] pas={news.getTitle(),news.getContent(),news.getStaffld(),news.getStatus(),news.getCreatedTime(),news.getEndTime()};
        return baseDao.executeUpdate(sql,pas);
    }

    /**
     * 修改新闻
     * @param news
     * @return
     */
    @Override
    public int updateNews(News news) {
        String sql="update  news  set title=?,content=?,status=?,createdTime=?,endTime=? where id=?";
        Object[] pas={news.getTitle(),news.getContent(),news.getStatus(),news.getCreatedTime(),news.getEndTime(),news.getId()};
        return baseDao.executeUpdate(sql,pas);
    }


}
