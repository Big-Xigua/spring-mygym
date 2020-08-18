package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.RoleService;
import com.aaa.mygym.service.impl.RoleServiceImpl;
import com.aaa.mygym.util.IntegerUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "UpdateOrAddRoleServlet",urlPatterns = "/UpdateOrAddRoleServlet")
public class UpdateOrAddRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseDto responseDto=new ResponseDto();
        Integer id=IntegerUtils.ToInteger(request.getParameter("id"));
        String roleName=request.getParameter("roleName");
        String description=request.getParameter("description");
        Integer status=IntegerUtils.ToInteger(request.getParameter("status"));
        RoleService roleService=new RoleServiceImpl();
        try {
            int len=roleService.updateOrAddRole(id,roleName,description,status);
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
                responseDto.setMessage("请求成功");
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
