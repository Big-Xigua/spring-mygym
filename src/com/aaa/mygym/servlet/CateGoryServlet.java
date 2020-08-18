package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.CateGoryService;
import com.aaa.mygym.service.RechargeRuleService;
import com.aaa.mygym.service.impl.CateGoryServiceImpl;
import com.aaa.mygym.service.impl.RechargeRuleServicelmpl;
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
 * 获得商品分类表
**/

@WebServlet(name = "CateGoryServlet" ,urlPatterns = "/CateGoryServlet")
public class CateGoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        CateGoryService cateGoryService=new CateGoryServiceImpl();
        //获取参数
        Integer pageNumber = IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize = IntegerUtils.ToInteger(request.getParameter("pageSize"));
        String searchName = request.getParameter("searchName");
        Integer searchStatus = IntegerUtils.ToInteger(request.getParameter("searchStatus"));
        try {
            responseDto.setData(cateGoryService.getAllCateGoryList(pageNumber,pageSize,searchName,searchStatus));
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setMessage("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setMessage(e.getMessage());
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
