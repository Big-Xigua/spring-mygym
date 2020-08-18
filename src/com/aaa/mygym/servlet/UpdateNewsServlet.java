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
/**
 * 修改新闻公告信息
**/ 
@WebServlet(name = "UpdateNewsServlet",urlPatterns = "/UpdateNewsServlet")
public class UpdateNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     Integer id= IntegerUtils.ToInteger(request.getParameter("id"));
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        int status=IntegerUtils.ToInteger(request.getParameter("status"));
        String createdTime=request.getParameter("createdTime");
        String endTime=request.getParameter("endTime");
        System.out.println(status);
        int len=0;
        NewsService newsService=new NewsServiceImpl();
        try {
            len=newsService.updateNews(title,content,status,createdTime,endTime,id);
            ResponseDto responseDto=new ResponseDto();
            responseDto.setData(len);
            responseDto.setMessage("请求成功");
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            response.getWriter().print(new Gson().toJson(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
