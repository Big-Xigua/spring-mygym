/**
 * 初始化
 **/
$(function () {
    cardManage.initList();
})
var cardManage = {};


/**
 * 根据id查找会员
 **/
cardManage.search = function () {
    if ($("#serchCardId").val() == "") {
        toastr['error']("请输入正确的账号");
    }
    $.ajax({
        url: "/GetCardInfoByIdServlet",
        type: 'post',
        data: {
            "serchCardId": $("#serchCardId").val(),
        },
        dataType: 'json',
        success: function (result) {
            if (result.data == 1 || result.data == "") {
                toastr['error']("账号未找到");
            } else {
                toastr['success']("读卡成功");
                $("#userId").val(result.data.userId);
                $("#userName").val(result.data.userName);
                $("#amout").val(result.data.amount);
                $("#cardId").val(result.data.cardId);
                $("#cardLevel").val(result.data.name);
                $("#credit").val(result.data.credit);
                getUserInfoById(result.data.userId);
                cardManage.initList1();
                cardManage.initList2();
            }
        }
    })
}

/**
 * 加载会员信息之后下方的会员基本的信息
 **/
function getUserInfoById(ros) {
    $("#userIdShow").text(""),
        $("#userNameShow").text(""),
        $("#phoneShow").text(""),
        $("#adressShow").text(""),
        $("#idNoShow").text(""),
        $("#statusShow").text(""),
        $("#cardIdShow").text(""),
        $("#amountShow").text(""),
        $("#cardLevelShow").text(""),
        $("#sexShow").text("")
    $.ajax({
        url: "/GetUserByIdServlet",
        type: 'post',
        data: {
            "pageNumber": 1,
            "pageSize": 100000,
            "searchId": ros,
            "searchName": $("#userNameShow").text()
        },
        dataType: 'json',
        success: function (result) {
            var sexShow = result.data.list[0].sex;
            if (sexShow == 1) {
                sexShow = "男";
            } else {
                sexShow = "女";
            }
            var statusShow = result.data.list[0].status;
            if (statusShow == 1) {
                statusShow = "正常";
            } else if (statusShow == 0) {
                statusShow = "挂失";
            } else if (statusShow == 2) {
                statusShow = "冻结";
            }
            $("#userIdShow").text(result.data.list[0].userId),
                $("#userNameShow").text(result.data.list[0].userName),
                $("#phoneShow").text(result.data.list[0].phone),
                $("#adressShow").text(result.data.list[0].address),
                $("#idNoShow").text(result.data.list[0].idCard),
                $("#statusShow").text(statusShow),
                $("#cardIdShow").text(result.data.list[0].cardId),
                $("#amountShow").text(result.data.list[0].amount),
                $("#cardLevelShow").text($("#cardLevel").val()),
                $("#sexShow").text(sexShow);
        }

    })
}

/**
 * 模态框
 **/
cardManage.initList = function () {
    $("#cardList").bootstrapTable({
        url: "/GetAllCardInfoServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: cardManage.queryParams, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 5, //默认每页加载行数
        pageList: [10, 25, 50, 100], //每页可选择记录数
        strictSearch: true,
        showColumns: false, // 是否显示所有的列
        showRefresh: false, // 是否显示刷新按钮
        minimumCountColumns: 2, // 最少允许的列数
        clickToSelect: true, // 是否启用点击选中行
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        showToggle: false, // 是否显示详细视图和列表视图的切换按钮
        cardView: false, // 是否显示详细视图
        detailView: false, // 是否显示父子表
        smartDisplay: false,
        onClickRow: function (e, row, element) {
            $(".success").removeClass("success");
            $(row).addClass("success");
        },
        responseHandler: function (result) {
            if (result != null) {
                return {
                    'total': result.data.count, //总条数
                    'rows': result.data.list //所有的数据
                };
            }
            return {
                'total': 0, //总条数
                'rows': [] //所有的数据
            }
        },
        //列表显示
        columns: [{
            field: 'id',
            title: "编号",
            visible: false
        }, {
            field: 'cardId',
            title: "会员卡号"
        }, {
            field: 'userName',
            title: "持卡人姓名"
        }, {
            field: 'userId',
            title: "会员ID"
        }, {
            field: 'levelName',
            title: "会员等级"
        }, {
            field: 'amount',
            title: "卡余额"
        }, {
            field: 'credit',
            title: "会员积分"
        }, {
            field: 'status',
            title: "状态",
            formatter: function (value) {
                switch (value) {
                    case 1 :
                        return "<span class='label label-info'>启用</span>";
                    case 2 :
                        return "<span class='label label-danger'>禁用</span>";
                }
            }
        }
        ]
    });
}
/**
 * 双击表格加载 指定区域的数据
 **/
$("#myModal").on('dbl-click-row.bs.table', function (e, row) {
    $("#cardId").val(row.cardId);
    $("#userName").val(row.userName);
    $("#userId").val(row.userId);
    $("#amout").val(row.amount);
    $("#cardLevel").val(row.levelName);
    $("#credit").val(row.credit);
    $("#myModal").modal('hide');
    getUserInfoById(row.userId);
    cardManage.flash();
    cardManage.initList1();
    cardManage.initList2();
    toastr['success']("读卡成功");

})

cardManage.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchOderId": "",
        "searchName": ""
    }
}
cardManage.queryParams1 = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchOderId": "",
        "searchName": $("#cardId").val()
    }
}

/**
 * 加载充值规则
 **/
cardManage.initRole = function () {
    $.ajax({
        url: "/GetCardAllRuleServlet",
        type: 'get',
        dataType: 'json',
        success: function (result) {
            if (result.status == 1) {
                var res = result.data;
                for (var i = 0; i < res.length; i++) {
                    var opt = $("<option value='" + res[i].id + "'>" + res[i].Name + "</option>");
                    $("#ruleId").append(opt);
                }
            }
        }
    })
}

/**
 加载会员详情
 */
cardManage.show = function () {
    $("#myModal").modal('show');
    cardManage.initList();
    $("#myModal").bootstrapTable('refresh', {pageNumber: 1});
    cardManage.flash();
}
/**
 * 刷新bootstrap
 */
cardManage.flash = function () {
    //重新加载 bootstrap table
    $("#orderList").bootstrapTable('refresh', {pageNumber: 1});
    $("#rechargeList").bootstrapTable('refresh', {pageNumber: 1});
    $("#userShow").bootstrapTable('refresh', {pageNumber: 1});
}
/**
 * 关闭模态框
 **/
$("#myModal1").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#cardForm").data('bootstrapValidator').resetForm();
    $("#cardForm")[0].reset();
})

/**
 * 会员消费记录
 **/
cardManage.initList1 = function () {
    $("#orderList").bootstrapTable({
        url: "/GetOderAllServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: cardManage.queryParams1, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 5, //默认每页加载行数
        pageList: [10, 25, 50, 100], //每页可选择记录数
        strictSearch: true,
        showColumns: false, // 是否显示所有的列
        showRefresh: false, // 是否显示刷新按钮
        minimumCountColumns: 2, // 最少允许的列数
        clickToSelect: true, // 是否启用点击选中行
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        showToggle: false, // 是否显示详细视图和列表视图的切换按钮
        cardView: false, // 是否显示详细视图
        detailView: false, // 是否显示父子表
        smartDisplay: false,
        onClickRow: function (e, row, element) {
            $(".success").removeClass("success");
            $(row).addClass("success");
        },
        responseHandler: function (result) {
            if (result != null) {
                return {
                    'total': result.data.count, //总条数
                    'rows': result.data.list //所有的数据
                };
            }
            return {
                'total': 0, //总条数
                'rows': [] //所有的数据
            }
        },
        //列表显示
        columns: [{
            field: 'id',
            title: "序号",
            visible: false
        }, {
            field: 'orderId',
            title: "订单编号"
        }, {
            field: 'cardId',
            title: "会员卡号"
        }, {
            field: 'cardType',
            title: "会员等级"
        }, {
            field: 'price',
            title: "应付金额"
        }, {
            field: 'pay',
            title: "实付金额"
        }, {
            field: 'credit',
            title: "商品积分"
        }, {
            field: 'status',
            title: "订单状态",
            formatter: function (value) {
                switch (value) {
                    case 0 :
                        return "未核验";
                    case 1 :
                        return "已核验";
                }
            }
        },
            {
                field: 'momo',
                title: "备注"
            }, {
                field: 'createdTime',
                title: "创建时间"
            }
        ]
    });
}
cardManage.queryParams2 = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchId": $("#cardId").val(),
        "searchName": $("#userName").text()
    }
}
/**
 * 会员充值记录
 **/
cardManage.initList2 = function () {
    $("#rechargeList").bootstrapTable({
        url: "/RechargeCardOrderServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: cardManage.queryParams2, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 5, //默认每页加载行数
        pageList: [10, 25, 50, 100], //每页可选择记录数
        strictSearch: true,
        showColumns: false, // 是否显示所有的列
        showRefresh: false, // 是否显示刷新按钮
        minimumCountColumns: 2, // 最少允许的列数
        clickToSelect: true, // 是否启用点击选中行
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        showToggle: false, // 是否显示详细视图和列表视图的切换按钮
        cardView: false, // 是否显示详细视图
        detailView: false, // 是否显示父子表
        smartDisplay: false,
        onClickRow: function (e, row, element) {
            $(".success").removeClass("success");
            $(row).addClass("success");
        },
        responseHandler: function (result) {
            console.log(result.data);
            if (result != null) {
                return {
                    'total': result.data.count, //总条数
                    'rows': result.data.list //所有的数据
                };
            }
            return {
                'total': 0, //总条数
                'rows': [] //所有的数据
            }
        },
        //列表显示
        columns: [{
            field: 'id',
            title: "序号",
            visible: false
        }, {
            field: 'userName',
            title: "会员姓名"
        }, {
            field: 'cardId',
            title: "会员卡号"
        }, {
            field: 'rechargeAmount',
            title: "充值金额"
        }, {
            field: 'afterAmount',
            title: "充值后金额"
        }, {
            field: 'beforeAmount',
            title: "充值前金额"
        }, {
            field: 'ruleId',
            title: "充值规则"
        }, {
            field: 'momo',
            title: "备注"
        }, {
            field: 'createdTime',
            title: "创建时间"
        }
        ]
    });
}

