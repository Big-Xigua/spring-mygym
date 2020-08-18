package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.RechargeRuleService;
import com.aaa.mygym.service.impl.RechargeRuleServicelmpl;
import com.aaa.mygym.util.IntegerUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelRechargeRuleServlet",urlPatterns = "/DelRechargeRuleServlet")
public class DelRechargeRuleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RechargeRuleService rechargeRuleService = new RechargeRuleServicelmpl();
        //返回参数
        ResponseDto responseDto=new ResponseDto();
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        Integer id = IntegerUtils.ToInteger(request.getParameter("ruleId"));
        responseDto.setData(rechargeRuleService.delRechargeRule(id));
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
