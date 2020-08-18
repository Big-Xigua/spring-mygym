package com.aaa.mygym.servlet;


import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.GetGoodsService;
import com.aaa.mygym.service.impl.GetGoodsServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddGoodsServlet" ,urlPatterns = "/AddGoodsServlet")
public class AddGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetGoodsService getGoodsService=new GetGoodsServiceImpl();
        String goodsId = request.getParameter("goodsId");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        Integer type = Integer.parseInt(request.getParameter("type"));
        Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Integer unitId = Integer.parseInt(request.getParameter("unitId"));
        double price = Double.parseDouble(request.getParameter("price"));
        ResponseDto responseDto = new ResponseDto();
        try {
           int len =  getGoodsService.addGoods(goodsId, name, code,  type,  unitId,  price, categoryId);
           if (len != 0 ){
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
