package com.aaa.mygym.servlet;


import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.UserService;
import com.aaa.mygym.service.impl.UserServiceImpl;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 修改会员信息
**/ 
@WebServlet(name = "UpdateUserServlet" , urlPatterns = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        Integer status = Integer.parseInt(request.getParameter("status"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Integer cardId = Integer.parseInt(request.getParameter("cardId"));
        String idCard = request.getParameter("idCard");
        ResponseDto responseDto = new ResponseDto();

        try {
            int len = userService.updateUserById(userName,phone,status, address, idCard, cardId, userId);
            System.out.println(len);
            responseDto.setMessage("成功");
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setData(len);
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
