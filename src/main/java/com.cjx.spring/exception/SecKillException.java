package com.cjx.spring.exception;

/**
 * 秒杀业务相关异常
 */
public class SecKillException extends RuntimeException{

    public SecKillException(String message) {
        super(message);
    }

    public SecKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
