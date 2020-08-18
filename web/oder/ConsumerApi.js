var  a =1;
var GetCardInfoByIdServlet = "/GetCardByCardIdServlet";
var cardListUrl = "/GetAllCardInfoServlet";
var initRoleUrl = "/GetCardAllRuleServlet";
var initRechargeRuleUrl="/GetRechargeRuleServlet";
var oderListUrl = "/GetOderAllServlet";
var rechargeCardOrder = "/RechargeCardOrderServlet";

var ConsumerUrl= "/ConsumerServlet";
/**
根据选中的会员获取会员信息
 */
var getUserAllUrl = "/GetAllUserInfoServlet";


var goodsListUrl="/ConsumGoodsListServlet";

var orderManage= {};
var goodsManage ={};
var cardManage={};

$(function () {
    goodsManage.initList();
})


cardManage.show=function(){
    $("#myModal").modal('show');
    cardManage.initList();
}
/**
 * 订单列表
 */

goodsManage.initList = function () {
    $("#goodsList").bootstrapTable({
        url: "/ConsumerGoodsListServlet", //请求路径
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
            title: "商品编号"
        }, {
            field: 'name',
            title: "商品名称"
        }, {
            field: 'code',
            title: "商品数量"
        }, {
            field: 'categoryName',
            title: "商品类型",
        }, {
            field: 'unitName',
            title: "单位",
        }, {
            field: 'price',
            title: "价格"
        }]
    });
}

/**
 * @author WDH
 * @date 2019/11/20   
 * 传递参数
**/
goodsManage.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchGoodsId": $("#searchGoodsId").val(),
        "searchName": $("#searchName").val()
    }
}



/**
 * @author WDH
 * @date 2019/11/20
 * 搜索
**/
goodsManage.search = function () {
    //bootstrapTable 刷新
    $("#goodsList").bootstrapTable('refresh');
}

/**
 * 搜索会员
 */
cardManage.search = function () {
    $.ajax({
        url: "/GetCardInfoByIdServlet",
        type: 'post',
        data: {
            "cardId": $("#serchCardId").val(),
        },
        dataType: 'json',
        success: function (result) {
            if(result.status==1){
                if(result.data[0].status==1){
                    toastr['success']("读卡成功");
                    $("#userId").text(result.data[0].userId);
                    $("#userName").text(result.data[0].userName);
                    $("#amout").text(result.data[0].amount);
                    $("#cardId").text(result.data[0].cardId);
                    $("#cardLevel").text(result.data[0].levelName);
                    $("#credit").text(result.data[0].credit);
                    // getUserInfoById(result.data.userId);
                    // cardManage.initList1();
                    // cardManage.initList2();
                }else if(result.data[0].status==0){
                    toastr['error']("此卡已挂失");
                }
                }else {
                toastr['error']("此卡已销毁");
            }
            // if (result.data == 1) {
            //     toastr['error']("账号未找到");
            // }else {
            //     toastr['success']("读卡成功");
            //     $("#userId").text(result.data[0].userId);
            //     $("#userName").text(result.data[0].userName);
            //     $("#amout").text(result.data[0].amount);
            //     $("#cardId").text(result.data[0].cardId);
            //     $("#cardLevel").text(result.data[0].levelName);
            //     $("#credit").text(result.data[0].credit);
            //     // getUserInfoById(result.data.userId);
            //     // cardManage.initList1();
            //     // cardManage.initList2();
            // }
        }
    })
}

/**
 * 双击表格加载 指定区域的数据
**/
$("#myModal").on('dbl-click-row.bs.table', function (e, row) {
    $("#cardId").text(row.cardId);
    $("#userName").text(row.userName);
    $("#userId").text(row.userId);
    $("#amout").text(row.amount);
    $("#cardLevel").text(row.levelName);
    $("#credit").text(row.credit);
    $("#myModal").modal('hide');
    toastr['success']("读卡成功");
})

$("#goodsList").on('click-row.bs.table', function (e, row) {

    orderManage.initConsumer();
    var tr = $("<tr></tr>");
    var td = $("<td>"+row.goodsId+"</td>");
    var td1 = $("<td>"+row.name+"</td>");
    var td2 = $("<td>"+1+"</td>");
    var td3 = $("<td>"+row.price+"</td>");
    var td4 = $("<td><button class='btn btn-danger' onclick='del(this)' >删除</button></td>");
    tr.append(td);
    tr.append(td1);
    tr.append(td2);
    tr.append(td3);
    tr.append(td4);
    tr.prop("id","trList"+a);
    $("#bugGoodsTb").append(tr);
    a++;
    var sum = 0;
    $("#buyGoods tbody tr").each(function () {
        var td = $(this).find("td");
        sum += Number(td.eq(3).text());
    })
    $("#orderPrice").text(sum);
})

function del(obj){
    $(obj).parent().parent().remove();
    $("#orderCode").text( $("#orderCode").text()-1);
    $("#orderPrice")


    var sum = 0;
    $("#buyGoods tbody tr").each(function () {
        var td = $(this).find("td");
        sum += Number(td.eq(3).text());
    })
    $("#orderPrice").text(sum);

}

/**
 * 加载模态框内表格
 */
cardManage.initList = function () {
    $("#cardList").bootstrapTable({
        url:"/GetAllCardInfoServlet", //请求路径
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
            console.log(result.data.count);
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
 * 获取时间戳作为消费单号
 */

orderManage.initConsumer=function(){

    var sum = 0;
    $("#buyGoods tbody tr").each(function () {
        var td = $(this).find("td");
        sum += Number(td.eq(3).text());
    })

    var timestamp = new Date().getTime();
    timestamp+="";
    $("#orderNumber").text(timestamp.substring(6));
    //初始化消费时间
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth();
    var day = date.getDate();
    var hour = date.getHours();
    var min = date.getMinutes();
    var sec = date.getSeconds();
    $("#orderTime").text(year + "-" + (month + 1) + "-" + day + " " + hour + ":" + min + ":" + sec);

    //显示商品数量
    $("#orderCode").text($("#buyGoods").find("tr").length);
}
orderManage.submit= function () {
    var a=true;
    if (Number($("#pay").text())>Number($("#amountShow").text())){
        toastr['error']("账户余额不足");
        a=false;
    }
    if (a){

    var arr = [];
    $("#buyGoods tbody tr").each(function () {
        var td = $(this).find("td");
        arr.push(td.eq(0).text());
    })
    var sum = 0;
    $("#buyGoods tbody tr").each(function () {
        var td = $(this).find("td");
        sum += Number(td.eq(3).text());
    })
    $.ajax({
        url:"/UpdateConsumeOrderServlet",
        type: 'post',
        data: {
            "ruleCardId":$("#cardId").text(),
            "ruleOrderId":$("#orderNumber").text(),
            "goodsId":arr.toString(),
             "rulePay":$("#pay").val(),
             "rulePrice":sum,
            "ruleMomo":$("#momo").val(),


        },
        dataType: 'json',
        success:function (result) {
            toastr['success']("消费成功");
            location.reload();
            $("#consumerList").modal('hide');
        }
    })
    }
}
/**
 * 加载订单模态框
 */
orderManage.show=function () {
    console.log($("#buyGoods tr:visible").length);
    if ($("#buyGoods tr:visible").length>1){
        if($("#cardId").text()==""){
        toastr['info']("您正处于游客模式");
    }else {
        $("#pay").prop("","");
        toastr['success']("您正处于会员刷卡模式");
    }
    $("#orderNum").text($("#orderNumber").text());
    $("#payShow").text($("#orderPrice").text());
    $("#codeShow").text($("#orderCode").text());
    $("#amountShow").text($("#amout").text());


    $("#pay").blur(function () {
        $("#pay-price").text(Number($("#pay").val())-Number($("#payShow").text()));
    });

    $("#consumerList").modal('show');
    }else {
        toastr['error']("请选择商品");
    }
}
