package com.aaa.mygym.entity;

import java.util.Date;

public class Card {
    /**
     * id
     */
    private Integer id;

    /**
     * cardid
     */
    private Integer cardId;
    /**
     * idCard
     * 身份证号
     */
    private String idCard;

    /**
     * userid
     */
    private Integer userId;
    /**
     * 会员名字
     */
    private String userName;
    /**
     * 员工Id
     */
    private Integer staffId;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    /**
     * amount
     */
    private Double amount;

    /**
     * 卡积分
     */
    private Integer credit;

    /**
     * 状态(0:正常 1:挂失)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 卡等级
     */
    private String cardType;
    private Integer level;

    private String levelName;

    /**
     * momo
     */
    private  String  momo;


    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", amount=" + amount +
                ", credit=" + credit +
                ", status=" + status +
                ", createdTime=" + createdTime +
                ", cardType='" + cardType + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
