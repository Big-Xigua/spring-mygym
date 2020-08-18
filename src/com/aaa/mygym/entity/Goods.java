package com.aaa.mygym.entity;

/**
 * 商品实体类
 * @author
 */
public class Goods {
    /**
     * ID
     */
    private String id ;
    /**
     * 商品单位名称
     */
    private  String unitName;
    /**
     * 商品价格
     */
    private double price;
    /**
     * 商品类别
     */
    private Integer categoryId;
    /**
     * 商品类别名称
     */
    private  String categoryName;


    /**
     * 商品编号
     */
    private String goodsId ;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 数量
     */
    private String code;
    /**
     * 商品状态(0:在售，1：下架)
     */
    private Integer status;
    /**
     * 商品类型(1:普通商品  0: 服务商品)
     */
    private Integer type;

    /**
     * 商品单位
     */
    private Integer unitId;

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", unitName='" + unitName + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", unitId=" + unitId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }
}
