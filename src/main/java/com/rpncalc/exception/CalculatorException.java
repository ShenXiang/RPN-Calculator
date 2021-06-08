package com.rpncalc.exception;

import lombok.Getter;

/**
 * BusinessException
 *
 * Common exception.
 * @author shenxiang
 */
@Getter
public class CalculatorException extends Exception {

    private final Integer code;
    private final String message;

    public CalculatorException(ErrorCodeEnum code) {
        this.message = code.getMessage();
        this.code = code.getCode();
    }

    public CalculatorException(ErrorCodeEnum code, String message) {
        this.message = message;
        this.code = code.getCode();
    }
}
