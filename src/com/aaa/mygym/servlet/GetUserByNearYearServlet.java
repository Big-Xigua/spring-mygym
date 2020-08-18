package com.aaa.mygym.servlet;

import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.UserService;
import com.aaa.mygym.service.impl.UserServiceImpl;
import com.aaa.mygym.util.DateUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 获取会员年份时间
**/ 
@WebServlet(name = "GetUserByNearYearServlet" ,urlPatterns = "/GetUserByNearYearServlet")
public class GetUserByNearYearServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserServiceImpl();
        int[] ints = DateUtils.getMonthByNearYear();
        List<Map<String, Object>> mapList = userService.getDataByNearYear();
        double[] doubles=new double[12];
        for (int i = 0; i < doubles.length; i++) {
            for (int k = 0; k < mapList.size(); k++) {
                if (ints[i]==Integer.parseInt(mapList.get(k).get("month")+"")){
                    doubles[i] = Double.parseDouble(mapList.get(k).get("amount") + "");
                }
            }
        }
        ResponseDto responseDto=new ResponseDto();
        Map<String,Object> map=new HashMap<>();
        map.put("ints",ints);
        map.put("doubles",doubles);
        responseDto.setData(map);
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
