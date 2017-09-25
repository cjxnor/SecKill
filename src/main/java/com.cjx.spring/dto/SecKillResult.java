package com.cjx.spring.dto;

/**
 * View Object
 * 所有 ajax 请求的返回类型
 * 封装 json 结果
 */
public class SecKillResult <T> {

    //标识请求是否成功
    private boolean success;

    private T data;

    private String error;


    public SecKillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SecKillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
