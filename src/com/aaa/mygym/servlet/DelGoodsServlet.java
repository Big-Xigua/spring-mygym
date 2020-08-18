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
 * 删除（伪删除）商品
**/

@WebServlet(name = "DelGoodsServlet",urlPatterns = "/DelGoodsServlet")
public class DelGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetGoodsService getGoodsService=new GetGoodsServiceImpl();
        Integer goodsId = IntegerUtils.ToInteger(request.getParameter("goodsId"));
        Integer goodsStatus = IntegerUtils.ToInteger(request.getParameter("goodsStatus"));
        ResponseDto responseDto = new ResponseDto();
        try {
            int len = getGoodsService.delGoods(goodsId,goodsStatus);
            if (len > 0){
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
        doPost(request,response);
    }
}
