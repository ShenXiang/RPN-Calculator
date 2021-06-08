package com.rpncalc.exception;

import lombok.Getter;

/**
 * CalculatorException
 *
 * Common exception.
 * @author shenxiang
 */
@Getter
public class CalculatorException extends RuntimeException {

    private final Integer code;
    private final String message;

    public CalculatorException(ErrorCodeEnum code) {
        this.message = code.getMessage();
        this.code = code.getCode();
    }
}
