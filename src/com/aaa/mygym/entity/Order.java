package com.aaa.mygym.entity;



import com.aaa.mygym.util.DateUtils;

import java.util.Date;

/**订单表
 * @author Administrator
 */
public class Order {
    /**
     * order的ID
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 会员卡号
     */
    private Integer cardId;
    /**
     * 卡类型（卡等级）
     */
    private Integer cardType;

    /**
     * 卡等级名字
     */
    private String  cardTypeName;

    /**
     *
     */


    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }
    /**
     * 应付金额
     */
    private  double price;
    /**
     * 实付金额
     */
    private  double pay;
    /**
     * 商品积分
     */
    private  Integer credit;
    /**
     * 订单状态（0 未核验  1 已核验）
     */
    private  Integer status;
    /**
     * 备注
     */
    private  String momo;
    /**
     * 消费日期
     */
    private  String createdTime;


    public Integer getId() {
        return id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public double getPrice() {
        return price;
    }

    public double getPay() {
        return pay;
    }

    public Integer getCredit() {
        return credit;
    }

    public String getMomo() {
        return momo;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = DateUtils.toFormat(createdTime);
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id =" + id +
                ", orderId =" + orderId +
                ", cardId ='" + cardId +
                ", cardType ='" + cardType +
                ", price ='" + price +
                ", pay ='" + pay +
                ", credit ='" + credit +
                ", status =" + status +
                ", momo =" + momo + '\'' +
                ", createdTime =" + createdTime +
                '}';
    }
}
