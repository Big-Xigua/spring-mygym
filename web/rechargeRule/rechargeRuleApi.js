/**
 * 初始化
 **/
$(function (){
    rechargeManager.initList();
})

/**
 * 定义函数
 **/
var rechargeManager = {};

/**
 * 加载列表
 **/
rechargeManager.initList = function () {
    $("#rechargeRuleList").bootstrapTable({
        url: "/GetRechargeRuleServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: rechargeManager.queryParams, //传递参数(*)
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
            field: 'name',
            title: "规则名字"
        }, {
            field: 'startMoney',
            title: "起充金额"
        }, {
            field: 'coefficient',
            title: "充值系数"
        }, {
            field: 'status',
            title: "状态",
            formatter: function (value) {
                if (value == 1) {
                    return '<span class="label label-info">启用</span>';
                } else {
                    return '<span class="label label-danger">禁用</span>';
                }
            }
        }, {
            field: 'createdTime',
            title: "创建时间"
        }, {
            field: 'endTime',
            title: "结束时间"
        }, {
            field: 'operation',
            events: buttonOperateEvent,
            title: '操作',
            formatter: function (value, row, index) {
                return rechargeManager.buttonOption(value, row, index);
            }
        }]
    });
}

/**
 * 添加事件  给 updateRule/delRule  添加了 点击事件
 **/
window.buttonOperateEvent = {
    'click .updateRule': function (e, value, row, index) {
        //row 这一行的数据
        rechargeManager.update(row);
    },
    'click .delRule': function (e, value, row, index) {
        rechargeManager.del(row);
    }
}

/**
 * 给操作添加 对象 button
 **/
rechargeManager.buttonOption = function (value, row, index) {
    var returnButton = [];
    returnButton.push('<button class="btn btn-info updateRule">修改</button>');
    if (row.status == 1) {
        returnButton.push('<button class="btn btn-danger delRule">删除</button>');
    } else {
        returnButton.push('<button class="btn btn-success delRule">启用</button>');
    }
    return returnButton.join('');
}

/**
 * 传递参数
 **/
rechargeManager.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber,
        "pageSize": params.pageSize,
        "searchStartTime": $("#searchStartTime").val(),
        "searchEndTime": $("#searchEndTime").val(),
        "searchName": $("#searchName").val(),
        "searchStatus": $("#searchStatus").val()
    }
}
/**
 * 搜索按钮
 **/
rechargeManager.search = function () {
    //重新加载 bootstrap table
    $("#rechargeRuleList").bootstrapTable('refresh', {pageNumber: 1})
}

/**
 * 规则修改
 **/
rechargeManager.update = function (row) {
    $("#myModal").modal('show');
    $("#ruleId").val(row.id);
    $("#ruleName").val(row.name);
    $("#ruleCoff").val(row.coefficient);
    $("#ruleStatus").val(row.status);
    $("#ruleCreatedDate").val(row.createdTime);
    $("#ruleEndDate").val(row.endTime);
    $("#ruleAmount").val(row.startMoney);
};

/**
 * 规则删除
 **/
rechargeManager.del = function (row) {
    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: "/DelRechargeRuleServlet",
                type: "post",
                data: {
                    "ruleId": row.id
                },
                dataType: 'json',
                success: function (result) {
                    if (result.data >= 1) {
                        toastr['success']("删除成功");
                        $("#rechargeRuleList").bootstrapTable('refresh');
                    } else {
                        toastr['success']("删除失败");
                    }
                }
            })
        }
    })
}

/**
 * 新增
 **/
rechargeManager.add = function () {
    var date = new Date();
    var dd = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    $("#ruleCreatedDate").val(dd);
    $("#myModal").modal('show');
}

/**
 * 关闭模态框  触发操作
 * 1.将验证重置
 * 2.重置表单内容
 **/
$("#myModal").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#ruleForm").data('bootstrapValidator').resetForm();
    $("#ruleForm")[0].reset();
})

/**
 * 确认按钮
 **/
rechargeManager.operate = function () {
    var bootstrapValidator = $("#ruleForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url: "/UpdateRechargeRuleByIdServlet",
            type: "post",
            data: {
                "ruleId": $("#ruleId").val(),
                "ruleName": $("#ruleName").val(),
                "ruleCoff": $("#ruleCoff").val(),
                "ruleStatus": $("#ruleStatus").val(),
                "ruleCreatedDate": $("#ruleCreatedDate").val(),
                "ruleEndDate": $("#ruleEndDate").val(),
                "ruleAmount": $("#ruleAmount").val()
            },
            dataType: 'json',
            success: function (result) {
                if (result.data >= 1) {
                    toastr['success']("修改成功");
                    $("#rechargeRuleList").bootstrapTable('refresh');
                    $("#myModal").modal('hide');
                } else {
                    toastr['success']("修改失败");
                }
            }
        })
    }
}

/**
 * 验证
**/
$("#ruleForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        ruleName: {
            validators: {
                notEmpty: {
                    message: "规则名字不能为空"
                }
            }
        },
        ruleAmount: {
            validators: {
                notEmpty: {
                    message: "起充金额不能为空"
                }
            }
        },
        ruleCoff: {
            validators: {
                notEmpty: {
                    message: "充值系数不能为空"
                }
            }
        },
        ruleCreatedDate: {
            validators: {
                notEmpty: {
                    message: "创建时间不能为空"
                }
            }
        },
        ruleEndDate: {
            validators: {
                notEmpty: {
                    message: "结束时间不能为空"
                }
            }
        }
    }
});
