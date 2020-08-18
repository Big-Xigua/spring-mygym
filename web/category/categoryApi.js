/**
 * 初始化
 */
$(function () {
    categoryManager.initList();
});
/**
 * 定义函数
 */
var categoryManager={};



/**
 * 加载列表
 */
categoryManager.initList = function () {
    $("#categoryList").bootstrapTable({
        url: "/CateGoryServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: categoryManager.queryParams, //传递参数(*)
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
            field: 'goodsId',
            title: "商品编号",
        }, {
            field: 'name',
            title: "商品名字"
        }, {
            field: 'categoryName',
            title: "商品类型"
        }, {
            field: 'status',
            title: "状态",
            formatter: function (value) {
                switch (value) {
                    case 1 :
                        return "<span class='label label-info'>下架</span>";
                    case 0 :
                        return "<span class='label label-danger'>在售</span>";
                }
            }
        // }, {
        //     field: 'operation',
        //     events: buttonOperateEvent,
        //     title: '操作',
        //     formatter: function (value, row, index) {
        //         return rechargeManager.buttonOption(value, row, index);
        //     }
        }]
    });
}
/**
 * 给搜索框传递参数
**/

categoryManager.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber,
        "pageSize": params.pageSize,
        "searchName": $("#searchName").val(),
        "searchStatus": $("#searchStatus").val()
    }
}
/**
 * 当我们点击搜索按钮的时候，table进行刷新，并跳转到第一页
**/

categoryManager.search = function () {
    //重新加载 bootstrap table
    $("#categoryList").bootstrapTable('refresh', {pageNumber: 1})
}

