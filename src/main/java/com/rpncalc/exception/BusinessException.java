package com.rpncalc.exception;

import lombok.Getter;

/**
 * BusinessException
 *
 * Common exception.
 * @author shenxiang
 */
@Getter
public class BusinessException extends Exception {

    private final String code;
    private final String message;

    public BusinessException(ErrorCodeEnum code) {
        this.message = code.getMessage();
        this.code = code.getCode();
    }

    public BusinessException(ErrorCodeEnum code, String message) {
        this.message = message;
        this.code = code.getCode();
    }
}
