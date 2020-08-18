package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.ConsumeService;
import com.aaa.mygym.service.impl.ConsumeServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name ="UpdateConsumeOrderServlet",urlPatterns = "/UpdateConsumeOrderServlet")
public class UpdateConsumeOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        ResponseDto responseDto=new ResponseDto();
        ConsumeService consumeService=new ConsumeServiceImpl();
        String ruleOrderId=req.getParameter("ruleOrderId");
        String ruleCardId=req.getParameter("ruleCardId");
        String rulePrice=req.getParameter("rulePrice");
        String rulePay=req.getParameter("rulePay");
        String ruleMomo=req.getParameter("ruleMomo");
        String goodsId=req.getParameter("goodsId");
        try{
            Map<String, Object> map = consumeService.insertOrder( ruleOrderId, ruleCardId, rulePrice, rulePay, ruleMomo, goodsId);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setData(map);
            responseDto.setMessage("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().print(new Gson().toJson(responseDto));
    }
}
