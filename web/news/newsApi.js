var newsManage={};
/**
 * 初始化
 */
$(function () {
    newsManage.getAll();
})
/**
 * 获取所有新闻信息
 */
newsManage.getAll=function () {
    $("#newsList").bootstrapTable({
        url: "/GetAllNewsServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: newsManage.queryParams, //传递参数(*)
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
            title: "排序",
            // visible: false
        }, {
            field: 'title',
            title: "标题"
        }, {
            field: 'status',
            title: "公告状态",
            formatter: function (value) {
                switch (value) {
                    case 1:
                        return "<span class='label label-info'>启用</span>";
                        break;
                    case 0:
                        return "<span class='label label-danger'>禁用</span>";
                        break;
                }
            }
        },
            {
                field: 'staffName',
                title: "发布人"
            },
            {
                field: 'createdTime',
                title: "创建日期",
            },
            {
                field: 'endTime',
                title: "结束日期",
            }, {
                field: 'operation',
                events: buttonOperateEvent,
                title: '操作',
                formatter: function (value, row, index) {
                    return newsManage.buttonOption(value, row, index);
                }
            },
        ]

    })
}
/**
 * 传递参数
 * @param params
 * @returns {{pageNumber: *, searchName: *, pageSize: *, createdTime: *, endTime: *, searchTitle: *}}
 */
newsManage.queryParams=function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchTitle": $("#searchTitle").val(),
        "searchName": $("#searchName").val(),
        "createdTime":$("#startTime").val(),
        "endTime":$("#endTime1").val()
    }
}
/**
 * 按钮源
 * @type {{"click .delNews": Window.buttonOperateEvent.click .delNews, "click .updateNews": Window.buttonOperateEvent.click .updateNews}}
 */
window.buttonOperateEvent={
    'click .updateNews':function (e,value,row,index) {
        newsManage.update(row);
    },
    'click .delNews':function (e,value,row,index) {
        newsManage.del(row);
    }
}
/**
 * 增加按钮
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
newsManage.buttonOption =function (value,row,index) {
    var returnButton=[] ;
    returnButton.push('<button Class="btn btn-info updateNews">修改</button>');
    returnButton.push('<button Class="btn btn-danger delNews">禁用/启用</button>');
    return returnButton.join('');
}
/**
 * 搜索
 */
newsManage.search=function () {
    //刷新页面
    $("#newsList").bootstrapTable('refresh',{pageNumber:1});
}
/**
 * 修改
 * @param row
 */
newsManage.update=function (row) {
    $("#newsId").val(row.id);
    $("#Title").val(row.title);
    $("#createdTime").val(row.createdTime);
    $("#staffName").val(row.staffName);
    $("#status").val(row.status);
    $("#endTime").val(row.endTime);
    $("#newsContent").val(row.content);
    $("#myModal").modal('show');
}
/**
 * 删除
 * @param row
 */
newsManage.del = function (row) {
    /**
     * 一般情况下删除要加confirm
     */
    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: "/DelNewsServlet",
                type: 'post',
                data: {
                    "id": row.id,
                    "status":row.status,
                },
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    if (result.status == 1) {
                        toastr['success']("删除成功");
                        $("#newsList").bootstrapTable('refresh',{pageNumber:1});
                    } else {
                        toastr['error']("删除失败");
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
    $("#newsForm").data('bootstrapValidator').resetForm();
    $("#newsForm")[0].reset();
})

/**
 * 确认按钮
 */
newsManage.operate = function () {
    var bootstrapValidator = $("#newsForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url: "/UpdateNewsServlet",
            type: 'post',
            data: {
                "id": $("#newsId").val(),
                "status": $("#status").val(),
                "title": $("#Title").val(),
                "content": $("#newsContent").val(),
                "createdTime":$("#createdTime").val(),
                "endTime":$("#endTime").val()
            },
            dataType: 'json',
            success: function (result) {
                if (result.status > 0) {
                    toastr['success']("操作成功");
                    $("#newsList").bootstrapTable('refresh',{pageNumber:1});
                    $("#myModal").modal('hide');
                } else {
                    toastr['error']("操作失败");
                }
            }
        })
    }
}
/**
 * 模态框验证
 */
$("#newsForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        Title: {
            validators: {
                notEmpty: {
                    message: "新闻/公告标题不能为空"
                }
            }
        },
        newsContent: {
            validators: {
                notEmpty: {
                    message: "新闻/公告内容不能为空"
                }
            }
        },
        createdTime: {
            validators: {
                notEmpty: {
                    message: "开始时间不能为空"
                }
            }
        },
        endTime: {
            validators: {
                notEmpty: {
                    message: "结束时间不能为空"
                }
            }
        }
    }
});
/**
 * 添加
 */
newsManage.add=function () {
    var userName= window.localStorage.getItem("userName");
    var staffId=window.localStorage.getItem("staffId");
    alert(staffId);
    $("#staffName1").val(userName);
    $("#staffId").val(staffId);
    $("#myModal1").modal('show');
}
/**
 * 添加确认按钮
 */
newsManage.operate1 = function () {
    var bootstrapValidator = $("#newsForm1").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url: "/AddNewsServlet",
            type: 'post',
            data: {
                "newsTitle1": $("#newsTitle1").val(),
                "status1": $("#status1").val(),
                "staffId":$("#staffId").val(),
                "createdTime1": $("#createdTime1").val(),
                "newsEndTime1": $("#newsEndTime1").val(),
                "newsContent1": $("#newsContent1").val()
            },
            dataType: 'json',
            success: function (result) {
                if (result.status > 0) {
                    toastr['success']("操作成功");
                    $("#myModal1").modal('hide');
                    $("#newsList").bootstrapTable('refresh',{pageNumber:1});
                } else {
                    toastr['error']("操作失败");
                }
            }
        })
    }
}
/**
 * 添加模态框验证
 */
$("#newsForm1").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        newsTitle: {
            validators: {
                notEmpty: {
                    message: "新闻/公告标题不能为空"
                }
            }
        },
        newsContent: {
            validators: {
                notEmpty: {
                    message: "新闻/公告内容不能为空"
                }
            }
        },
        createdTime: {
            validators: {
                notEmpty: {
                    message: "开始时间不能为空"
                }
            }
        },
        newsEndTime: {
            validators: {
                notEmpty: {
                    message: "结束时间不能为空"
                }
            }
        }
    }
});
/**
 * 关闭模态框
 */
$("#myModal1").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#newsForm1").data('bootstrapValidator').resetForm();
    $("#newsForm1")[0].reset();
})