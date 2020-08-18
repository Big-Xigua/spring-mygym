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
import java.util.Map;

@WebServlet(name = "GetRoleListServlet",urlPatterns = "/GetRoleListServlet")
public class GetRoleListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNumber = IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize = IntegerUtils.ToInteger(request.getParameter("pageSize"));
        String searchName = request.getParameter("searchName");
        String roleId=request.getParameter("role");
        RoleService roleService = new RoleServiceImpl();
        Map<String, Object> map = null;
        try {
            map = roleService.getAllRoleInfo(pageNumber,pageSize,searchName,roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        responseDto.setData(map);
        responseDto.setMessage("请求成功");
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
