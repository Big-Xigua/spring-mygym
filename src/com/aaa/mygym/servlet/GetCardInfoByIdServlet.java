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
import java.util.Map;
/** 
 * @author
 * @date
 * 刷卡界面读卡操作
**/ 
@WebServlet(name = "GetCardInfoByIdServlet",urlPatterns = "/GetCardInfoByIdServlet")
public class GetCardInfoByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        ResponseDto responseDto = new ResponseDto();
        try {
                Integer userId = IntegerUtils.ToInteger(request.getParameter("serchCardId"));
                Map<String ,Object> map = userService.getCardInfoById(userId);
                if (map.size() > 0 ){
                    responseDto.setStatus(ResponseDto.SUCCESS_CODE);
                    responseDto.setData(map);
                    responseDto.setMessage("请求成功");
                    response.getWriter().print(new Gson().toJson(responseDto));
                }else {
                    responseDto.setStatus(ResponseDto.FAILURE_CODE);
                    responseDto.setData(1);
                    response.getWriter().print(new Gson().toJson(responseDto));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
