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
 * 会员新增中获取最后一个卡的id
**/ 
@WebServlet(name = "GetLastCardIdServlet",urlPatterns = "/GetLastCardIdServlet")
public class GetLastCardIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        Integer getLastCardId = Integer.parseInt(userService.getLastCardId())+1;
        ResponseDto responseDto = new ResponseDto();

        responseDto.setData(getLastCardId);
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        responseDto.setMessage("成功");
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
