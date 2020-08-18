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
 * 订单修改
**/

@WebServlet(name = "UpdateOderServlet",urlPatterns = "/UpdateOderServlet")
public class UpdateOderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseDto responseDto=new ResponseDto();
        Integer orderId = IntegerUtils.ToInteger(request.getParameter("orderId"));
        Integer cardId = IntegerUtils.ToInteger(request.getParameter("cardId"));
        Integer cardType = IntegerUtils.ToInteger(request.getParameter("cardType"));
        Double price =Double.parseDouble(request.getParameter("price"));
        Double pay=Double.parseDouble(request.getParameter("pay"));
        Integer credit = IntegerUtils.ToInteger(request.getParameter("credit"));
        Integer status = IntegerUtils.ToInteger(request.getParameter("status"));
        String momo = request.getParameter("momo");
        OrderService orderService=new OrderServiceImpl();
        try {
            int len= orderService.updateOrderByOrderId(orderId,cardId,cardType,price,pay,credit,status,momo);
            if (len >0) {
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
                responseDto.setMessage("修改成功");
                responseDto.setData(len);
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
