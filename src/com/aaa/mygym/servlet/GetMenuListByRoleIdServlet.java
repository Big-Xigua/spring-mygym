package com.aaa.mygym.servlet;


import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.MenuService;
import com.aaa.mygym.service.impl.MenuServiceImpl;
import com.aaa.mygym.util.IntegerUtils;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetMenuListByRoleIdServlet", urlPatterns = "/GetMenuListByRoleIdServlet")
public class GetMenuListByRoleIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger("GetMenuListByRoleIdServlet");
        MenuService menuService = new MenuServiceImpl();
        Integer roleId = IntegerUtils.ToInteger(request.getParameter("roleId"));
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setData(menuService.getMenuListByRoleId(roleId));
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setMessage("操作成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseDto.setMessage(e.getMessage());
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
