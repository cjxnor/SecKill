package com.cjx.spring.dto;

/**
 * 暴露秒杀地址 DTO
 */
public class Exposer {

    //是否开启秒杀
    private boolean exposed;

    // md5 时一种加密措施
    private String md5;

    //商品 id
    private long skId;

    //系统当前时间（毫秒）
    private long nowTime;

    //秒杀开启时间（毫秒）
    private long startTime;

    //秒杀结束时间（毫秒）
    private long endTime;


    public Exposer(boolean exposed, String md5, long skId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.skId = skId;
    }

    public Exposer(boolean exposed, long skId, long nowTime, long startTime, long endTime) {
        this.exposed = exposed;
        this.skId = skId;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Exposer(boolean exposed, long skId) {
        this.exposed = exposed;
        this.skId = skId;
    }


    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSkId() {
        return skId;
    }

    public void setSkId(long skId) {
        this.skId = skId;
    }

    public long getNowTime() {
        return nowTime;
    }

    public void setNowTime(long nowTime) {
        this.nowTime = nowTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", skId=" + skId +
                ", nowTime=" + nowTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

