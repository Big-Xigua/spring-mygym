package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.CardType;
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
import java.util.List;

@WebServlet(name = "GetCardTypeListServlet",urlPatterns = "/GetCardTypeListServlet")
public class GetCardTypeListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        List<CardType> cardTypeList =  userService.getCardTypeList();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(cardTypeList);
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        responseDto.setMessage("查询成功");
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
