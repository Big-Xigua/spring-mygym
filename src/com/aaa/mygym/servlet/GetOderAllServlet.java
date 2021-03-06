package com.aaa.mygym.servlet;


import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.OrderService;
import com.aaa.mygym.service.impl.OrderServiceImpl;
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
 * 订单查询
**/

@WebServlet(name = "GetOderAllServlet",urlPatterns = "/GetOderAllServlet")
public class GetOderAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService=new OrderServiceImpl();
        Integer pageNumber = IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize = IntegerUtils.ToInteger(request.getParameter("pageSize"));
        String searchOderId = request.getParameter("searchOderId");
        String searchName = request.getParameter("searchName");
        try {
            //返回参数
            ResponseDto responseDto = new ResponseDto();
            responseDto.setData( orderService.getAllOrderInfo(pageNumber, pageSize,searchOderId, searchName));
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
