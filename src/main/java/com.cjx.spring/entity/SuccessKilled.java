package com.cjx.spring.entity;


import java.util.Date;

/**
 *  表 success_killed 对应的实体类
 */
public class SuccessKilled {

    private long skId;

    private long userPhone;

    private short state;

    private Date createTime;

    //一对多配置
    private Stock stock;

    public long getSkId() {
        return skId;
    }

    public void setSkId(long skId) {
        this.skId = skId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "skId=" + skId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", stock=" + stock +
                '}';
    }
}
