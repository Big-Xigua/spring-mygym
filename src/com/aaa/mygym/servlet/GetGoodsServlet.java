package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.GetGoodsService;
import com.aaa.mygym.service.impl.GetGoodsServiceImpl;
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
 * 获得商品列表
**/ 
@WebServlet(name = "GetGoodsServlet", urlPatterns = "/GetGoodsServlet")
public class GetGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseDto responseDto = new ResponseDto();
        GetGoodsService getGoodsService = new GetGoodsServiceImpl();

        Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));

        String searchName = request.getParameter("searchName");
        Integer searchStatus = IntegerUtils.ToInteger(request.getParameter("searchStatus"));
        try {
            responseDto.setData(getGoodsService.selAllGoods(pageNumber,pageSize,searchName,searchStatus));
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setMessage("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
