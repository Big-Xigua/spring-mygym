package com.aaa.mygym.service.impl;

import com.aaa.mygym.dao.NewsDao;
import com.aaa.mygym.dao.impl.NewsDaoImpl;
import com.aaa.mygym.entity.News;
import com.aaa.mygym.service.NewsService;
import com.aaa.mygym.util.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao=new NewsDaoImpl();

    /**
     * 搜索
     * @param pageNumber
     * @param pageSize
     * @param searchTitle
     * @param searchName
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> getAllNews(Integer pageNumber, Integer pageSize,String searchTitle, String searchName,String createdTime,String endTime) throws Exception {
        if (pageNumber == null || pageNumber == 0 )  {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null||pageSize==0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber=(pageNumber-1)*pageSize;

       List<News> list= newsDao.getAllNews(pageNumber,pageSize,searchTitle,searchName,createdTime,endTime);
        int count=newsDao.getAllNewsCount(searchTitle,searchName,createdTime,endTime);
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("count",count);
        return map;
    }

    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public int delNewsById(Integer id,Integer status)throws Exception {
        if (id == null ) {
            throw new BusinessException("ID不能为空");
        }
        if (status == null) {
            throw  new BusinessException("状态不能为空");
        }
        return newsDao.delNewsById(id,status);
    }

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
    @Override
    public int updateNews(String title, String content, int status, String createdTime, String endTime, int id) throws Exception {
        int len=0;
        if (StringUtils.isBlank(title)) {
            throw new BusinessException("标题不能为空");
        }
        if (StringUtils.isBlank(content)) {
            throw new BusinessException("内容不能为空");
        }
        if (StringUtils.isBlank(createdTime)) {
            throw new BusinessException("创建时间不能为空");
        }
        if (StringUtils.isBlank(endTime)) {
            throw new BusinessException("结束时间不能为空");
        }
        News news=new News();
            news.setTitle(title);
            news.setContent(content);
            news.setStatus(status);
            news.setCreatedTime(createdTime);
            news.setEndTime(endTime);
            news.setId(id);
            len=newsDao.updateNews(news);

        return len;
    }

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
    @Override
    public int addNewsInfo(Integer status, String newsTitle,String createdTime, Integer staffId, String newsEndTime, String newsContent) throws Exception {
        News news = new News();
        if(StringUtils.isBlank(newsTitle)){
            throw new BusinessException("新闻标题不能为空");
        }
        if(StringUtils.isBlank(createdTime)){
            throw new BusinessException("新闻创建时间不能为空");
        }
        if(StringUtils.isBlank(newsEndTime)){
            throw new BusinessException("新闻结束不能为空");
        }
        if(StringUtils.isBlank(newsContent)){
            throw new BusinessException("新闻内容不能为空");
        }
        if(staffId==0){
            throw new BusinessException("发布人id不能为空");
        }
        news.setStaffld(staffId);
        news.setStatus(status);
        news.setTitle(newsTitle);
        news.setCreatedTime(createdTime);
        news.setEndTime(newsEndTime);
        news.setContent(newsContent);
        return newsDao.addNews(news);

    }
}
