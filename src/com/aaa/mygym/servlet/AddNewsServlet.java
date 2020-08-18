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
 * 新增新闻
**/
@WebServlet(name = "AddNewsServlet",urlPatterns = "/AddNewsServlet")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsService newsService = new NewsServiceImpl();
        String newsTitle1 = request.getParameter("newsTitle1");
        Integer status1 = IntegerUtils.ToInteger(request.getParameter("status1"));
        String createdTime1 = request.getParameter("createdTime1");
        String newsEndTime1 = request.getParameter("newsEndTime1");
        String newsContent1 = request.getParameter("newsContent1");
        Integer staffId=IntegerUtils.ToInteger(request.getParameter("staffId"));
        ResponseDto responseDto = new ResponseDto();
        try {
            int len = newsService.addNewsInfo(status1,newsTitle1,createdTime1,staffId,newsEndTime1,newsContent1);
            responseDto.setData(len);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setMessage("成功");
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
