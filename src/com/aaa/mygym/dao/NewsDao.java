package com.aaa.mygym.dao;

import com.aaa.mygym.entity.News;

import java.util.List;

public interface NewsDao {
 /**
  * 搜索
  * @param pageNumber
  * @param pageSize
  * @param searchTitle
  * @param searchName
  * @return
  */
 List<News> getAllNews(Integer pageNumber, Integer pageSize, String searchTitle, String searchName, String createdTime, String endTime);

 /**
  * 获取总数
  * @param searchTitle
  * @param searchName
  * @return
  */
 int getAllNewsCount(String searchTitle, String searchName, String createdTime, String endTime);

 /**
  * 根据id改状态
  * @param id
  * @param status
  * @return
  */
 int delNewsById(Integer id, Integer status);

 /**
  * 添加新闻
  * @param news
  * @return
  */
 int addNews(News news);

 /**
  * 修改新闻
  * @param news
  * @return
  */
 int updateNews(News news);

}
