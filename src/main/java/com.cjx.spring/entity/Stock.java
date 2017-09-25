package com.cjx.spring.entity;

import java.util.Date;

/**
 * 表 stock 对应的实体类
 */
public class Stock {

    private long skId;

    private String name;

    private int number;

    private Date startTime;

    private Date endTime;

    private Date createTime;


    public long getSkId() {
        return skId;
    }

    public void setSkId(long skId) {
        this.skId = skId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "Stock{" +
                "skId=" + skId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
