package com.aaa.mygym.servlet;


import com.aaa.mygym.entity.CateGory;
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
import java.util.List;

/**
 * @author
 * @date
**/

@WebServlet(name = "GetGoodsCategoryServlet" ,urlPatterns = "/GetGoodsCategoryServlet")
public class GetGoodsCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetGoodsService getGoodsService=new GetGoodsServiceImpl();
        List<CateGory> list = getGoodsService.GetGoodsCategory();
        ResponseDto responseDto= new ResponseDto();
        if (list == null){
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
            responseDto.setMessage("失败");
        }else {
            responseDto.setMessage("成功");
            responseDto.setData(list);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
