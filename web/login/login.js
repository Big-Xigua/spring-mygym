/**
 * 初始化
 **/
$(function () {

})

/**
 * 定义函数
**/
var loginManager = {};

loginManager.login = function () {
    $.ajax({
        url: "/LoginServlet",
        type: "post",
        data: {
            "userName": $("#loginName").val(),
            "password": $("#loginPwd").val()
        },
        dataType: "json",
        success: function (result) {
            if (result.status == 1 && result.data != null){
                toastr['success'](result.message);
                window.location.href = "index/views/main.html";
                /**
                 * localStorage 是一个数据库
                 * 只能存 键值对 而且是前端的 小型数据库 数据存放在 页面中
                 */
                window.localStorage.setItem("userName",result.data.staffName);
                window.localStorage.setItem("roleId",result.data.roleId);
                window.localStorage.setItem("staffId",result.data.staffId);
                window.localStorage.setItem("phone",result.data.phone);
                window.localStorage.setItem("idCard",result.data.idCard);
                window.localStorage.setItem("address",result.data.address);
                window.localStorage.setItem("createdTime",result.data.createdTime);
                window.localStorage.setItem("password",result.data.password);
            }else {
                toastr['error'](result.message);
            }
        }
    })
}

/*
$.ajax({
    url:"",
    type:"post",
    data:{},
    dataType:"json",
    success:function (result) {
        console.log(result);
    }
})
* */