package com.aaa.mygym.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @date
 * 会员实体类
**/ 
public class User {
    /**
     * 会员id
     */
    private Integer id;
    /**
     * 会员编号
     */
    private Integer userId;
    /**
     * 会员名字
     */
    private String userName;
    /**
     * 会员手机号
     */
    private String phone;
    /**
     * 会员状态
     */
    private Integer status;
    /**
     * 会员身份证号
     */
    private String idCard;
    /**
     * 会员生日
     */
    private String birthday;
    /**
     * 会员性别1男 2女
     */
    private Integer sex ;
    /**
     * 会员地址
     */
    private String address;
    /**
     * 会员详细地址
     */
    private String area;
    /**
     * 会员注册时间
     */
    private Date createdTime;
    /**
     * 会员卡id
     */
    private Integer cardId;
    /**
     * 会员卡余额
     */
    private Double amount;
    /**
     * 备注
     */
    private  String momo;
    /**
     * 会员积分
     */
    private  Integer credit;
    /**
     * 卡登记名称
     */
    private String levelName;
    /**
     * 员工id
     */
    private Integer staffId;

    /*private String createDate = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss").format(createdTime);*/

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    /**
     *
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", idCard='" + idCard + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", cardId=" + cardId +
                ", amount=" + amount +
                ", momo='" + momo + '\'' +
                ", credit=" + credit +
                ", levelName='" + levelName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
