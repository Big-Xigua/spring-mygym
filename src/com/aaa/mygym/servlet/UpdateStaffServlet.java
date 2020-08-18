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

@WebServlet(name = "UpdateStaffServlet",urlPatterns ="/UpdateStaffServlet")
public class UpdateStaffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        Integer staffId = IntegerUtils.ToInteger(request.getParameter("staffId"));
        String staffName = request.getParameter("staffName");
        String phone = request.getParameter("phone");
        String idCard = request.getParameter("idCard");
        Integer status = IntegerUtils.ToInteger(request.getParameter("status"));
        Integer roleId = IntegerUtils.ToInteger(request.getParameter("roleId"));
        String remake = request.getParameter("remake");
        String address = request.getParameter("address");
        StaffService staffService = new StaffServiceImpl();
        try {
            int len = staffService.updateStaffByStaffId(staffId,staffName,phone,idCard,status,roleId,remake,address);
            if (len > 0) {
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
                responseDto.setMessage("修改成功");
                responseDto.setData(len);
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
        doPost(request, response);
    }
}
