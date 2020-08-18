package com.aaa.mygym.entity;

import java.util.Date;
/** 
 * @author
 * @date
 * 消费记录实体类
**/ 
public class RechargeRecord {
    /**
     * id
     */
    private Integer id;

    /**
     * 会员卡Id
     */
    private Integer cardId;
    /**
     * 会员姓名
     */
    private String userName;
    /**
     * 充值金额
     */
    private Double rechargeAmount;

    /**
     * 充值后卡余额
     */
    private Double afterAmount;

    /**
     * 充值前卡余额
     */
    private Double beforeAmount;

    /**
     * 充值规则
     */
    private Integer ruleId;

    /**
     * 充值时间
     */
    private Date createdTime;

    /**
     * 操作员
     */
    private Integer staffId;

    /**
     * 备注
     */
    private String momo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getcardId() {
        return cardId;
    }

    public void setcardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Double getrechargeAmount() {
        return rechargeAmount;
    }

    public void setrechargeAmount(Double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Double getafterAmount() {
        return afterAmount;
    }

    public void setafterAmount(Double afterAmount) {
        this.afterAmount = afterAmount;
    }

    public Double getbeforeAmount() {
        return beforeAmount;
    }

    public void setbeforeAmount(Double beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    public Integer getruleId() {
        return ruleId;
    }

    public void setruleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Date getcreatedTime() {
        return createdTime;
    }

    public void setcreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getstaffId() {
        return staffId;
    }

    public void setstaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }
}
