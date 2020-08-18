package com.aaa.mygym.servlet;


import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.StaffService;
import com.aaa.mygym.service.impl.StaffServiceImpl;

import com.aaa.mygym.util.IntegerUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateStaffPassServlet",urlPatterns = "/UpdateStaffPassServlet")
public class UpdateStaffPassServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StaffService staffService = new StaffServiceImpl();
        Integer staffId = IntegerUtils.ToInteger(request.getParameter("staffId"));
        String password= request.getParameter("password");
        int len = staffService.updateStaffPass(staffId,password);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        responseDto.setMessage("修改成功");
        responseDto.setData(len);
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
