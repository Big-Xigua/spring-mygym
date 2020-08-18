package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.entity.Role;
import com.aaa.mygym.service.RoleService;
import com.aaa.mygym.service.impl.RoleServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetStaffAllRoleServlet",urlPatterns = "/GetStaffAllRoleServlet")
public class GetStaffAllRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleService roleService  = new RoleServiceImpl();
        List<Role> roleList =  roleService.getAllRole();
        ResponseDto responseDto = new ResponseDto();
        if (roleList == null) {
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
            responseDto.setMessage("获取失败");
        }else {
            responseDto.setMessage("获取成功");
            responseDto.setData(roleList);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
