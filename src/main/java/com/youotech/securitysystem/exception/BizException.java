package com.youotech.securitysystem.exception;

/**
 * @author sundongyang
 * @date 2017/8/3 0003
 * @time 08:37
 * @desc 自定义异常
 * @see
 */
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
