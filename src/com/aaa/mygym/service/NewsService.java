package com.aaa.mygym.service;

import java.util.Map;

/**
 * @author Administrator
 */
public interface NewsService {
    /**
     * 搜索
     * @param pageNumber
     * @param pageSize
     * @param searchTitle
     * @param searchName
     * @param createdTime
     * @param endTime
     * @return
     * @throws Exception
     */
    Map<String,Object> getAllNews(Integer pageNumber, Integer pageSize, String searchTitle, String searchName, String createdTime, String endTime) throws Exception;
    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    int  delNewsById(Integer id, Integer status) throws Exception;
    /**
     * 修改新闻
     * @param title
     * @param content
     * @param status
     * @param createdTime
     * @param endTime
     * @param id
     * @return
     * @throws Exception
     */
    int updateNews(String title, String content, int status, String createdTime, String endTime, int id)throws Exception;
    /**
     * 添加新闻
     * @param status
     * @param newsTitle
     * @param createdTime
     * @param staffId
     * @param newsEndTime
     * @param newsContent
     * @return
     * @throws Exception
     */
    int addNewsInfo(Integer status, String newsTitle, String createdTime, Integer staffId, String newsEndTime, String newsContent) throws Exception;
}
