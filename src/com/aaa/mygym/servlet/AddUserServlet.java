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
 * 新增会员
**/
@WebServlet(name = "AddUserServlet", urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String userId = userService.getLastUserId();
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String userLevel = request.getParameter("userLevel");
        String userStatus = request.getParameter("userStatus");
        String staffName = request.getParameter("staffName");
        String credit = request.getParameter("credit");
        Integer cardId = IntegerUtils.ToInteger(request.getParameter("cardId"));
        String amount = request.getParameter("amount");
        String idno = request.getParameter("idno");
        String userSex = request.getParameter("userSex");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String momo = request.getParameter("momo");
        String ruleId = request.getParameter("ruleId");
        String staffId = request.getParameter("staffId");
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        try {
            int len = userService.addUser(userName,userPhone,userLevel,userStatus,staffName,credit,amount,idno,userSex,province,city,address,momo,ruleId,staffId,cardId);
            int res1 = userService.createCard(cardId, Integer.parseInt(userId)+1+"", Double.parseDouble(amount), IntegerUtils.ToInteger(credit), IntegerUtils.ToInteger(userStatus), IntegerUtils.ToInteger(staffId), IntegerUtils.ToInteger(userLevel));
                responseDto.setData(cardId);
                responseDto.setData(len);
                responseDto.setData(res1);
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
                responseDto.setMessage("成功");
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(responseDto));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
