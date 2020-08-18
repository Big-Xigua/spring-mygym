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
 * @author
 * @date
 * 删除新闻
**/ 
@WebServlet(name = "DelNewsServlet",urlPatterns = "/DelNewsServlet")
public class DelNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Integer id= IntegerUtils.ToInteger(request.getParameter("id"));
            Integer status=IntegerUtils.ToInteger(request.getParameter("status"));
            NewsService newsService=new NewsServiceImpl();
        try {
            int len=newsService.delNewsById(id,status);
            ResponseDto responseDto=new ResponseDto();
            responseDto.setData(len);
            responseDto.setMessage("发送成功");
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
