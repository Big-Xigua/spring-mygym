package com.aaa.mygym.entity;

/**
 * @author
 * @date
 * 单位实体类
**/

public class Unit {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 单位状态
     */
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
