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
 * 删除（伪删除）消费订单
**/

@WebServlet(name = "DelOrderServlet",urlPatterns = "/DelOrderServlet")
public class DelOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService=new OrderServiceImpl();
        ResponseDto responseDto=new ResponseDto();
        Integer orderId = IntegerUtils.ToInteger(request.getParameter("orderId"));
        Integer status = IntegerUtils.ToInteger(request.getParameter("status"));
        try {
            int len = orderService.delOrder(orderId, status);
            if (len>0){
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            }else {
                responseDto.setStatus(ResponseDto.FAILURE_CODE);
            }
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
