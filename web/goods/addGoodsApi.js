
// var addGoodsManageUrl = "/AddGoodsServlet";
// var initGoodsUnitUrl ="/GetAllUnitServlet";
// var initGoodsCategoryUrl ="/GetGoodsCategoryServlet";
// var getLastGoodsIdUrl="/GetLastGoodsIdServlet";

$(function () {
    goodsManage.initUnitGoods();
    goodsManage.initCategoryGoods();
    goodsManage.getLastGoodsId();
})

var goodsManage = {};

goodsManage.getLastGoodsId= function(){
    $.ajax({
        url:"/GetLastGoodsIdServlet",
        type:'post',
        data: {
            "goodsId":$("#goodsId").val()
        },
        dataType:'json',
        success:function (result) {
            $("#goodsId").val(result);
        }
    })
}
/**
 * 提交增加信息
 */
goodsManage.add=function () {
    var bootstrapValidator = $("#AddGoodsForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()){
        Modal.confirm({
            msg: "确认当前操作"
        }).on(function (e) {
            if (e) {
                $.ajax({
                    url:"/AddGoodsServlet",
                    type:'post',
                    data:{
                        "goodsId":$("#goodsId").val(),
                        "name":$("#name").val(),
                        "code":$("#code").val(),
                        "type":$("#type").val(),
                        "unitId":$("#unitId").val(),
                        "price":$("#price").val(),
                        "categoryId":$("#categoryId").val(),
                    },
                    dataType:'json',
                    success:function (result) {
                        if(result != null){
                            toastr['success']("增加成功");
                            location.reload(true);
                        }else {
                            toastr['error']("增加失败");
                        }
                    }
                })
            }
        })

    }
}

/**
 * 模态框  单位下拉框查询
 */
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
goodsManage.initCategoryGoods = function () {
    $.ajax({
        url: "/GetGoodsCategoryServlet",
        type: 'get',
        dataType: 'json',
        success: function (result) {
            if (result.status == 1) {
                var res = result.data;
                for (var i = 0; i < res.length; i++) {
                    var opt = $("<option value='" + res[i].id + "'>" + res[i].name + "</option>");
                    $("#categoryId").append(opt);
                }
            }
        }
    })
}
/**
 * 表单验证
 */
$("#AddGoodsForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        goodsId: {
            validators: {
                notEmpty: {
                    message: "商品编码不能为空"
                }
            }
        },
        code:{
            validators:{
                notEmpty:{
                    message:"商品数量不能为空"
                },
                regexp:{
                    regexp:/^[0-9]+$/,
                    message:"请输入正确的货物数量"
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: "商品名称不能为空"
                }
            }
        },
        unitId: {
            validators: {
                notEmpty: {
                    message: "单位不能为空"
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: "商品类型不能为空"
                }
            }
        },
        price:{
            validators:{
                notEmpty:{
                    message:"商品售价不能为空"
                },
                regexp:{
                    regexp:/^[0-9]+$/,
                    message:"请输入正确的商品售价"
                }
            }
        },
        categoryId: {
            validators: {
                notEmpty: {
                    message: "商品类别不能为空"
                }
            }
        }
    }
});