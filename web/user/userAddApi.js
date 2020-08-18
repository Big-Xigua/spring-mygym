/**
 * 初始化
 */
$(function () {
    userManager.initCardType(); // 加载会员等级下拉框
    $("#staffName").val(localStorage.getItem("userName"))
    /*
     * 实现省市级联
     * 1.先创建省市json对象
     * 2.通过json数组.keys()方法取出json中所有的键
     * 3.将所有取出的键放入第一个下拉列表中：
     * 创建option标签，遍历数组将键值放入
     * 4.将创建的option标签放入复选框中
     * 5.当省级下拉列表选中改变时触发市级下拉列表变化，创建函数
     * 6.获取第一个复选框选中的键值
     * 7.根据键值取出当前键所有的value值
     * 8.创建option标签，将取得的所有value值通过遍历放入option标签中
     * 9.将创建的option标签放入市级的复选框
     */
    //1.创建省市级联json对象
    var data={
        "河南":["郑州","新乡","驻马店","开封"],
        "海南":["三亚","海口"],
        "安徽":["合肥","芜湖","马鞍山"],
        "云南":["昆明","大理","西双版纳","玉溪"],
        "湖南":["长沙","湘潭","张家界"]
    }
    //2.将jeson对象遍历，取出所有的key值存在集合obj中
    var obj=Object.keys(data);
    //遍历集合访问所有的key值
    for (var i = 0; i < obj.length; i++) {
        //将取出的key值放入select中
        var opt=$("<option>"+ obj[i]+"</option>");
        $("#province").append(opt);
    }
    //为第一个多选框添加单击事件
    $("#province").change(function(){
        //在改变之前将第二个select中的值清空
        $("#city").html("");
        //获取省级下拉列表中选中的键值放入value中
        var value=data[$("#province").val()];
        //将获取到的value值遍历
        for (var i = 0; i < value.length; i++) {
            //创建option标签，将取到的value值放在option中
            var opt =$("<option>"+value[i]+"</option>");
            //将创建的option值放入第二个select中
            $("#city").append(opt);
        }
    });
    getLastCardId();
});

/**
 * 获取会员表中最后一个卡id
 */
function getLastCardId (){
    $.ajax({
        url:"/GetLastCardIdServlet",
        type:'post',
        dataType:'json',
        success:function(result){
            $("#cardId").val(result.data);
        }
    })
}


/**
 * 定义函数
 * @type {{}}
 */
var userManager = {};

/**
 * 初始化 加载会员等级下拉框
 */
userManager.initCardType = function () {
    $.ajax({
        url: "/GetCardTypeListServlet",
        type: "post",
        dataType: "json",
        success: function (result) {
            if (result.data != null) {
                for (var i = 0; i < result.data.length; i++) {
                    var opt = $("<option data-id='" + result.data[i].id + "' value='" + result.data[i].rank + "'>" + result.data[i].name + "</option>");
                    $("#userLevel").append(opt);
                }
            }
        }
    })
}
/**
 * 验证 手机号时候存在
 */
var checkUserPhone = true;
userManager.checkUserPhone = function () {
    var userPhone = $("#userPhone").val();
    $.ajax({
        url: "/CheckOnlyUserServlet",
        type: "post",
        data: {
            "phone": userPhone
        },
        dataType: "json",
        success: function (result) {
            if (result.data == 1 && result.message != null) {
                toastr['error'](result.message);
                checkUserPhone = false;
            }
        }
    })
}
/**
 * 设置起充金额和初始积分
 */
userManager.setCredit = function () {
    $("#credit").val($("#userLevel").val());
    $("#amount").val($("#userLevel").val());
}

/**
 * 提交数据
 */
userManager.add = function () {
    var bootstrapValidator = $("#addUserForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (!bootstrapValidator.isValid()){
        if (!checkUserPhone) {
            toastr['error']("手机号已经被使用");
        }
        return false;
    }
    $.ajax({
        url: "/AddUserServlet",
        type: "post",
        data: {
            "cardId": $("#cardId").val(),
            "userName": $("#userName").val(),
            "userPhone": $("#userPhone").val(),
            "userLevel": $("#userLevel").val(),
            "userStatus": $("#userStatus").val(),
            "staffName": $("#staffName").val(),
            "credit": $("#credit").val(),
            "amount": $("#amount").val(),
            "idno": $("#idno").val(),
            "userSex": $("#userSex").val(),
            "province": $("#province").val(),
            "city": $("#city").val(),
            "address": $("#address").val(),
            "momo": $("#momo").val(),
            "ruleId":$("#userLevel option:selected").data('id'),
            "staffId":localStorage.getItem("staffId")
        },
        dataType: 'json',
        success: function (result) {
            toastr['success']("添加成功");
            location.reload(true);
        }
    })
};

/**
 * 点击确定触发验证
**/ 
$("#addUserForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        userName: {
            validators: {
                notEmpty: {
                    message: "名字不能为空"
                }
            }
        },
        userPhone: {
            validators: {
                notEmpty: {
                    message: "电话不能为空"
                }
            }
        },
        staffName: {
            validators: {
                notEmpty: {
                    message: "办卡人员不能为空"
                }
            }
        },
        credit: {
            validators: {
                notEmpty: {
                    message: "初始积分不能为空"
                }
            }
        },
        amount: {
            validators: {
                notEmpty: {
                    message: "起充金额不能为空"
                }
            }
        }
        ,
        idno: {
            validators: {
                notEmpty: {
                    message: "身份证号不能为空"
                }
            }
        }
    }
});

