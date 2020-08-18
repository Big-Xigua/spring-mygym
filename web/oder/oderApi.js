/*
 * 定义URl
**/
// var oderListUrl = "/GetOderAllServlet";
// var updateOderUrl = "/UpdateOderServlet";
// var delOderServletUrl = "/DelOderServlet";


/*
 * 初始化
**/
$(function () {
    oderManage.initList();
})
var oderManage = {};
/**
 * 加载table
 */
oderManage.initList = function () {
    $("#oderList").bootstrapTable({
        url: "/GetOderAllServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: oderManage.queryParams, //传递参数(*)
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
        },  {
            field: 'cardType',
            title: "会员等级",
            formatter: function (value) {
                switch (value) {
                    case 1 :
                        return "普通会员";
                    case 2 :
                        return "青铜会员";
                    case 3 :
                        return "白银会员";
                    case 4 :
                        return "黄金会员";
                    case 5 :
                        return "铂金会员";
                }
            }
        }, {
            field: 'price',
            title: "应付金额"
        },{
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
        }, {
            field: 'operation',
            events: buttonOperateEvent,
            title: '操作',
            formatter: function (value, row, index) {
                return oderManage.buttonOption(value, row, index);
            }
        }
        ]
    });
}
/**
 * 传递参数
 * @param params
 * @returns {{pageNumber: *, searchOderId: *, searchName: *, pageSize: *}}
 */
oderManage.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchOderId": $("#searchOderId").val(),
        "searchName": $("#searchName").val()
    }
}
/**
 * 按钮源 (按钮的操作事件)
 * @type {{"click .delOder": Window.buttonOperateEvent.click .delOder, "click .updateOder": Window.buttonOperateEvent.click .updateOder}}
 */
window.buttonOperateEvent = {
    'click .updateOder': function (e, value, row, index) {
        //row 这一行的数据
        oderManage.update(row);
    },
    'click .delOder': function (e, value, row, index) {
        oderManage.del(row);
    }
}
/**
 * 添加操作按钮
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
oderManage.buttonOption = function (value, row, index) {
    var returnButton = [];
    returnButton.push('<button class="btn btn-info updateOder">修改</button>');
    returnButton.push('<button class="btn btn-danger delOder">审核</button>');
    return returnButton.join('');
}
/**
 * 搜索
 */
oderManage.search = function () {
    //bootstrapTable 刷新
    $("#oderList").bootstrapTable('refresh');
}
/**
 * 修改
 * @param row
 */
oderManage.update = function (row) {

    $("#orderId").val(row.orderId);
    $("#cardId").val(row.cardId);
    $("#cardType").val(row.cardType);
    $("#price").val(row.price);
    $("#pay").val(row.pay);
    $("#credit").val(row.credit);
    $("#status").val(row.status);
    $("#momo").val(row.momo);
    $("#myModal").modal('show');
}
/**
 * 订单审核（删除）
 * @param row
 */
oderManage.del = function (row) {
    /**
     * 一般情况下删除要加confirm
     */
    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: "/DelOrderServlet",
                type: 'post',
                data: {
                    "orderId": row.orderId,
                    "status":row.status
                },
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    if (result.status == 1) {
                        toastr['success']("审核成功");
                        $("#oderList").bootstrapTable('refresh');
                    } else {
                        toastr['error']("审核失败");
                    }
                }
            })
        }
    })
}
/**
 * 关闭模态框
 */
$("#myModal").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#oderForm").data('bootstrapValidator').resetForm();
    $("#oderForm")[0].reset();
})
/**
 * 确认按钮
 */
oderManage.operate = function () {
    var bootstrapValidator = $("#oderForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url: "/UpdateOderServlet",
            type: 'post',
            data: {
                "orderId": $("#orderId").val(),
                "cardId": $("#cardId").val(),
                "cardType": $("#cardType").val(),
                "price": $("#price").val(),
                "pay": $("#pay").val(),
                "credit": $("#credit").val(),
                "status": $("#status").val(),
                "momo": $("#momo").val(),
            },
            dataType: 'json',
            success: function (result) {
                if (result.status >0) {
                    toastr['success']("操作成功");
                    $("#oderList").bootstrapTable('refresh');
                    $("#myModal").modal('hide');
                } else {
                    toastr['error']("操作失败");
                }
            }
        })
    }
}

/**
 * 验证
 */
$("#oderForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        cardId: {
            validators: {
                notEmpty: {
                    message: "会员卡号不能为空"
                }
            }
        }
    }
});

/**
 *  新增订单
 */
oderManage.add = function () {
    $("#myModal").modal('show');
}
