package com.cjx.spring.exception;

/**
 * 秒杀关闭异常（运行期异常）
 * 包括：秒杀时间到，秒杀商品数量不足等
 */
public class SecKillCloseException extends SecKillException{

    public SecKillCloseException(String message) {
        super(message);
    }

    public SecKillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
