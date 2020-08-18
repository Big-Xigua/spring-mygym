package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.CardService;
import com.aaa.mygym.service.RechargeRuleService;
import com.aaa.mygym.service.impl.CardServiceImpl;
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
 * 会员充值确定
**/ 
@WebServlet(name = "RechargeCardServlet",urlPatterns = "/RechargeCardServlet")
public class RechargeCardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CardService cardService = new CardServiceImpl();
        ResponseDto responseDto = new ResponseDto();
        RechargeRuleService rechargeRuleService = new RechargeRuleServicelmpl();
        Integer cardId = IntegerUtils.ToInteger(request.getParameter("userCardId"));
        Integer credit = IntegerUtils.ToInteger(request.getParameter("credit"));
        Double amount = Double.parseDouble(request.getParameter("amount"));
        Integer cardAmount = IntegerUtils.ToInteger(request.getParameter("cardAmount"));
        Integer rechargeRule = IntegerUtils.ToInteger(request.getParameter("rechargeRule"));
        Integer staffId = IntegerUtils.ToInteger(request.getParameter("staffId"));
        String momo = request.getParameter("momo");
        Double total = Double.parseDouble(request.getParameter("total"));
        Double sendAmount = Double.parseDouble(request.getParameter("sendAmount"));
        Integer userCredit = IntegerUtils.ToInteger(request.getParameter("userCredit"));
        try {
            int len = cardService.rechargeCard(cardId,total,rechargeRule);
            int len1 = rechargeRuleService.rechargeRecode(cardId,cardAmount,rechargeRule,staffId,amount);
            if (len > 0 && len1 > 0){
                responseDto.setMessage("请求成功");
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
                responseDto.setData(len);
                response.getWriter().print(new Gson().toJson(responseDto));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
