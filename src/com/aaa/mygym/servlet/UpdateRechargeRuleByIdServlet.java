package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.RechargeRuleService;
import com.aaa.mygym.service.impl.RechargeRuleServicelmpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateRechargeRuleByIdServlet",urlPatterns ="/UpdateRechargeRuleByIdServlet" )
public class UpdateRechargeRuleByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RechargeRuleService rechargeRuleService=new RechargeRuleServicelmpl();
        String ruleId = request.getParameter("ruleId");
        String ruleName = request.getParameter("ruleName");
        String ruleCoff = request.getParameter("ruleCoff");
        String ruleStatus = request.getParameter("ruleStatus");
        String ruleCreatedDate = request.getParameter("ruleCreatedDate");
        String ruleEndDate = request.getParameter("ruleEndDate");
        String ruleAmount = request.getParameter("ruleAmount");
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        try {
            int len = rechargeRuleService.updateRechargeRuleById(ruleId,ruleId,ruleName,ruleCoff,ruleStatus,ruleCreatedDate,ruleEndDate,ruleAmount);
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
        doPost(request, response);
    }
}
