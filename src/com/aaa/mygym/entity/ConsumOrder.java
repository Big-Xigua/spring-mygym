package com.aaa.mygym.entity;

/**
 * @author
 * @data
 * @describe
 */

public class ConsumOrder  {



    /**
     * id
     */
    private Integer id;

    /**
     * 订单编号
     */
    private Integer orderId;

    /**
     * 卡id
     */
    private Integer cardId;

    /**
     * 卡类型
     */
    private Integer cardType;

    /**
     * 应付金额
     */
    private Double price;

    /**
     * 实付金额
     */
    private Double pay;

    /**
     * 商品积分
     */
    private Integer credit;

    /**
     * 订单状态（0未核验 1已核验）
     */
    private Integer status;

    /**
     * 备注
     */
    private String momo;

    /**
     * 消费日期
     */
    private String createdTime;

    public ConsumOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    public String getCreatedtime() {
        return createdTime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdTime = createdtime;
    }

    @Override
    public String toString() {
        return "ConsumOrder{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", cardId=" + cardId +
                ", cardTye=" + cardType +
                ", price=" + price +
                ", pay=" + pay +
                ", credit=" + credit +
                ", status=" + status +
                ", momo='" + momo + '\'' +
                ", createdtime=" + createdTime +
                '}';
    }
}

