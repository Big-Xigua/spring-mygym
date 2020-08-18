package com.aaa.mygym.servlet;


import com.aaa.mygym.service.GetGoodsService;
import com.aaa.mygym.service.impl.GetGoodsServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取最后一个商品编号
 */
@WebServlet(name = "GetLastGoodsIdServlet" ,urlPatterns = "/GetLastGoodsIdServlet")
public class GetLastGoodsIdServlet extends HttpServlet {
    private GetGoodsService getGoodsService=new GetGoodsServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer integer = getGoodsService.getLastGoodsId();
        response.getWriter().print(integer);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
