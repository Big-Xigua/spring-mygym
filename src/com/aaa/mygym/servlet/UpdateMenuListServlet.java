package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.RoleService;
import com.aaa.mygym.service.impl.RoleServiceImpl;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateMenuListServlet",urlPatterns = "/UpdateMenuListServlet")
public class UpdateMenuListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleService roleService = new RoleServiceImpl();
        //获取参数
        Integer roleId = StringUtils.isBlank(request.getParameter("roleId")) ? 0 : Integer.parseInt(request.getParameter("roleId"));
        String resources = request.getParameter("getNodeList");
        //参数校验
        if (StringUtils.isBlank(resources)) {
            throw new RuntimeException("权限id不能为空");
        }
        String[] strings = resources.substring(1,resources.length()-1).split(",");
        Integer[] intarray = new Integer[strings.length];
        //将String数组转化为int数组
        for (int i = 0; i < strings.length; i++) {
            intarray[i] = Integer.parseInt(strings[i]);
        }
        try {
            int res = roleService.updateMenuList(roleId,intarray);
            //返回参数
            ResponseDto responseDto = new ResponseDto();
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setData(res > 0 ? 1 : 0);
            responseDto.setMessage("请求成功");
            response.getWriter().print(new Gson().toJson(responseDto));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
