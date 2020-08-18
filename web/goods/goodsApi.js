/**
 * 初始化
 **/
$(function () {
    goodsManage.initList();
})

/**
 * 定义函数
 **/
var goodsManage = {};

/**
 * 加载列表
 **/
goodsManage.initList = function () {
    $("#goodsList").bootstrapTable({
        url: "/GetGoodsServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: goodsManage.queryParams, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 10, //默认每页加载行数
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
            field: 'goodsId',
            title: "商品编号",
        }, {
            field: 'name',
            title: "商品名字"
        }, {
            field: 'code',
            title: "商品库存"
        }, {
            field: 'unitName',
            title: "商品单位",
        }, {
            field: 'price',
            title: "单价"
        }, {
            field: 'type',
            title: "商品类型",
            formatter: function (value) {
                switch (value) {
                    case 1 :
                        return "<span class='label label-info'>普通商品</span>";
                    case 0 :
                        return "<span class='label label-danger'>服务商品</span>";
                }
            }
        },{
            field: 'status',
            title: "商品状态",
            formatter: function (value) {
                switch (value) {
                    case 1 :
                        return "<span class='label label-danger'>下架</span>";
                    case 0 :
                        return "<span class='label label-success'>在售</span>";
                }
            }
        }
        , {
            field: 'operation',
            events: buttonOperateEvent,
            title: '操作',
            formatter: function (value, row, index) {
                return goodsManage.buttonOption(value, row, index);
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
        goodsManage.update(row);
    },
    'click .delRule': function (e, value, row, index) {
        goodsManage.del(row);
    }
}

/**
 * 给操作添加 对象 button
 **/
goodsManage.buttonOption = function(value, row, index){
    var returnButton = [];
    returnButton.push('<button class="btn btn-info updateRule">修改</button>');
    if (row.status==0){
        returnButton.push('<button class="btn btn-success delRule">在售</button>');
    } else {
        returnButton.push('<button class="btn btn-danger delRule">下架</button>');
    }
    return returnButton.join('');
}

/**
 * 传递参数
 **/
goodsManage.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber,
        "pageSize": params.pageSize,
        "searchName": $("#searchName").val(),
        "searchStatus": $("#searchStatus").val(),

    }
}

/**
 * 搜索按钮
 **/
goodsManage.search = function () {
    console.log("搜索")
    //重新加载 bootstrap table
    $("#goodsList").bootstrapTable('refresh', {pageNumber: 1})
}

/**
 * 规则修改
 **/
goodsManage.update = function (row) {
    console.log(row);
    $("#myModal").modal("show")

    $("#goodsId").val(row.goodsId);
    $("#goodsCode").val(row.code);
    $("#goodsName").val(row.name);
    $("#goodsPrice").val(row.price);
    $("#goodsType").val(row.type);
    $("#unitId").val(row.unitId);
    $("#goodsStatus").val(row.status);
};

/**
 * 商品删除
 **/
goodsManage.del = function (row) {
    $.ajax({
        url:"/DelGoodsServlet",
        type:"post",
        data:{
            "goodsId":row.goodsId,
            "goodsStatus":row.status
        },
        dataType:'json',
        success:function (result) {
            console.log(result);
            if (result.status ==1||result.status==0 ){
                toastr['success']('请求成功');
                $("#goodsList").bootstrapTable('refresh')

            } else {
                toastr['success']("删除失败");
            }
        }
    })
};


/**
 * 关闭模态框
 * 清除验证信息
**/

$("#myModal").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#goodsForm").data('bootstrapValidator').resetForm();
    $("#goodsForm")[0].reset();
})

/**
 * 确认按钮点击事件
**/

goodsManage.operate=function () {
    var bootstrapValidator = $("#goodsForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url:"/UpdateGoodsByIdServlet",
            type:"post",
            data:{
                "goodsId":$("#goodsId").val(),
                "goodsCode":$("#goodsCode").val(),
                "goodsName":$("#goodsName").val(),
                "goodsPrice":$("#goodsPrice").val(),
                "goodsType":$("#goodsType").val(),
                "unitId":$("#unitId").val(),
                "goodsStatus":$("#goodsStatus").val()
            },
            dataType:'json',
            success:function (result) {
                console.log(result);
                if (result.data >=1 ){
                    toastr['success']('修改成功');
                    $("#myModal").modal("hide");
                    $("#goodsList").bootstrapTable('refresh')

                } else {
                    toastr['success']("修改失败");
                }
            }
        })
    }
}
/**
 * 验证
 */
$("#goodsForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        goodsName: {
            validators: {
                notEmpty: {
                    message: "商品名称不能为空"
                }
            }
        },goodsType: {
            validators: {
                notEmpty: {
                    message: "商品类型不能为空"
                }
            }
        },goodsPrice: {
            validators: {
                notEmpty: {
                    message: "商品价格不能为空"
                }
            }
        },goodsCode: {
            validators: {
                notEmpty: {
                    message: "商品价格不能为空"
                }
            }
        },unitId: {
            validators: {
                notEmpty: {
                    message: "商品价格不能为空"
                }
            }
        },goodsType: {
            validators: {
                notEmpty: {
                    message: "商品价格不能为空"
                }
            }
        },goodsStatus: {
            validators: {
                notEmpty: {
                    message: "商品价格不能为空"
                }
            }
        }
    }
});
goodsManage.initUnitGoods = function () {
    $.ajax({
        url: "/GetAllUnitServlet",
        type: 'get',
        dataType: 'json',
        success: function (result) {
            if (result.status == 1) {
                var res = result.data;
                for (var i = 0; i < res.length; i++) {
                    var opt = $("<option value='" + res[i].id + "'>" + res[i].name + "</option>");
                    $("#unitId").append(opt);
                }
            }
        }
    })
}