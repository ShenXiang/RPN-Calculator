package com.rpncalc.exception;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * Success
     */
    SUCCESS(0, "Success"),

    /**
     * UNKNOWN ERROR
     */
    UNKNOWN_ERROR(-1, "Unknown error"),

    /**
     * INTERNAL ERROR
     */
    INTERNAL_ERROR(-2, "Internal error"),

    /**
     * Arithmetic Errors
     */
    DIVISION_BY_ZERO(-3, "Division by zero"),
    NEGATIVE_SQUARE_ROOT(-4, "Square root is negative"),

    /**
     * Invalid Expression
     */
    INSUFFICIENT_PARAMETERS(-5, "operator %s (position: %d): insufficient parameters"),
    INVALID_TOKEN(-6, "token %s (position: %d): invalid token"),
    ;

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCodeEnum valueOf(int code) {
        ErrorCodeEnum[] es = ErrorCodeEnum.values();
        return Arrays.stream(es).filter(e -> e.code == code).findAny().orElse(UNKNOWN_ERROR);
    }
}



