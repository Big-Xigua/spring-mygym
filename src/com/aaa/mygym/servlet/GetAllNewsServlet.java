package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.NewsService;
import com.aaa.mygym.service.impl.NewsServiceImpl;
import com.aaa.mygym.util.IntegerUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
/** 
 * @author
 * @date
 * 查找所有新闻
**/ 
@WebServlet(name = "GetAllNewsServlet",urlPatterns = "/GetAllNewsServlet")
public class GetAllNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNumber= IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize= IntegerUtils.ToInteger(request.getParameter("pageSize"));
        String  searchTitle=request.getParameter("searchTitle");
        String  searchName=request.getParameter("searchName");
        String createdTime=request.getParameter("createdTime");
        String endTime=request.getParameter("endTime");
        NewsService newsService=new NewsServiceImpl();
        try {
            Map<String, Object> map=newsService.getAllNews(pageNumber,pageSize,searchTitle,searchName,createdTime,endTime);
            ResponseDto responseDto=new ResponseDto();
            responseDto.setData(map);
            responseDto.setMessage("请求成功");
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            Gson gson=new Gson();
            response.getWriter().print(gson.toJson(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
    }
}
