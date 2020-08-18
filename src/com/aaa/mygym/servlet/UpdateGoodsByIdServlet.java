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
/**
 * 根据商品编号修改商品列表
**/
@WebServlet(name = "UpdateGoodsByIdServlet",urlPatterns = "/UpdateGoodsByIdServlet")
public class UpdateGoodsByIdServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetGoodsService getGoodsService=new GetGoodsServiceImpl();
        ResponseDto responseDto=new ResponseDto();

        String goodsId = request.getParameter("goodsId");
        String goodsName = request.getParameter("goodsName");
        String goodsCode = request.getParameter("goodsCode");
        Integer goodsType = Integer.parseInt(request.getParameter("goodsType"));
        Integer unitId = Integer.parseInt(request.getParameter("unitId"));
        double goodsPrice = Double.parseDouble(request.getParameter("goodsPrice"));
        Integer goodsStatus = Integer.parseInt(request.getParameter("goodsStatus"));


        try {
            int len = getGoodsService.updategoodsById(goodsId,
                    goodsCode, goodsName, goodsPrice,
                    goodsType, unitId, goodsStatus);
            responseDto.setMessage("修改成功");
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setData(len);
            response.getWriter().print(new Gson().toJson(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
