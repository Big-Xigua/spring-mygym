package com.aaa.mygym.servlet;

import com.aaa.mygym.dao.RoleDao;
import com.aaa.mygym.dao.impl.RoleDaoImpl;
import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.RoleService;
import com.aaa.mygym.service.impl.RoleServiceImpl;
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
 * 删除（不是物理删除）
**/
@WebServlet(name = "DelRoleServlet",urlPatterns = "/DelRoleServlet")
public class DelRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleService roleService=new RoleServiceImpl();
        Integer id=Integer.parseInt(request.getParameter("id"));
        Integer status=Integer.parseInt(request.getParameter("status"));
        ResponseDto responseDto = new ResponseDto();
        try {
            int len=roleService.delRole(id,status);
            responseDto.setStatus(len);
            responseDto.setData(ResponseDto.SUCCESS_CODE);
            responseDto.setMessage("成功");
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
