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
 * @author
 * @date
 * 新增会员的手机号码验证
**/ 
@WebServlet(name = "CheckOnlyUserServlet",urlPatterns = "/CheckOnlyUserServlet")
public class CheckOnlyUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String phone = request.getParameter("phone");
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setMessage(userService.checkOnlyUser(phone));
            responseDto.setData(1);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
