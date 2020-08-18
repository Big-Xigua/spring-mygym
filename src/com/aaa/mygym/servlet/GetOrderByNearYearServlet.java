package com.aaa.mygym.servlet;


import com.aaa.mygym.entity.ResponseDto;
import com.aaa.mygym.service.OrderService;
import com.aaa.mygym.service.impl.OrderServiceImpl;
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


@WebServlet(name = "GetOrderByNearYearServlet",urlPatterns = "/GetOrderByNearYearServlet")
public class GetOrderByNearYearServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService=new OrderServiceImpl();
         int[] ints= DateUtils.getMonthByNearYear();
        List<Map<String, Object>> mapList = orderService.getDataByNearYear();
        double[] doubles = new double[12];
        for (int i = 0; i < doubles.length; i++) {
            for (int j = 0; j < mapList.size(); j++) {
                if (ints[i] == Integer.parseInt(mapList.get(j).get("month") + "")) {
                    doubles[i] = Double.parseDouble(mapList.get(j).get("amount") + "");
                }
            }
        }
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        Map<String,Object> map = new HashMap<>();
        map.put("ints" ,ints);
        map.put("doubles",doubles);
        responseDto.setData(map);
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
    }
}
