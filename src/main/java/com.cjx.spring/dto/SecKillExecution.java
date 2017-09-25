package com.cjx.spring.dto;

import com.cjx.spring.entity.SuccessKilled;
import com.cjx.spring.enums.SecKillStateEnum;

/**
 * 封装秒杀执行后结果
 */
public class SecKillExecution {

    /**
     * 默认 JSON 类库转化枚举类型会出问题，
     * 所以不设置枚举属性，而在构造方法中使用枚举
     */

    private long skId;

    //秒杀执行结果状态
    private int state;

    //秒杀执行结果状态说明
    private String stateInfo;

    //秒杀成功对象
    private SuccessKilled successKilled;


    public SecKillExecution(long skId, SecKillStateEnum stateEnum) {
        this.skId = skId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public SecKillExecution(long skId, SecKillStateEnum stateEnum, SuccessKilled successKilled) {
        this.skId = skId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }


    public long getSkId() {
        return skId;
    }

    public void setSkId(long skId) {
        this.skId = skId;
    }

    public int getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SecKillExecution{" +
                "skId=" + skId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
