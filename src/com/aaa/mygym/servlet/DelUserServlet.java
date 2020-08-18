package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.UserService;
import com.aaa.mygym.service.impl.UserServiceImpl;
import com.aaa.mygym.util.IntegerUtils;
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
 * 会员的逻辑删除
**/ 
@WebServlet(name = "DelUserServlet" ,urlPatterns = "/DelUserServlet")
public class DelUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = IntegerUtils.ToInteger(request.getParameter("userId"));
        Integer status = IntegerUtils.ToInteger(request.getParameter("status"));
        UserService userService = new UserServiceImpl();
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        try {
            int len = userService.deluser(userId,status);
            if (len > 0){
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
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
