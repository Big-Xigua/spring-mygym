package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.RechargeRule;
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
import java.util.List;
/** 
 * @author
 * @date
 * 会员充值页面的会员等级
**/ 
@WebServlet(name = "GetCardAllRuleServlet",urlPatterns = "/GetCardAllRuleServlet")
public class GetCardAllRuleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RechargeRuleService rechargeRuleService = new RechargeRuleServicelmpl();
        List<RechargeRule> rechargeRules = rechargeRuleService.rechargeRule();
        ResponseDto responseDto = new ResponseDto();
        if (rechargeRules == null) {
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
            responseDto.setMessage("获取失败");
        }else {
            responseDto.setMessage("获取成功");
            responseDto.setData(rechargeRules);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
