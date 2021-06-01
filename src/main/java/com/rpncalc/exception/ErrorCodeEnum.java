package com.rpncalc.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * Success
     */
    SUCCESS("000000", "Success"),

    /**
     * INTERNAL ERROR
     */
    INTERNAL_ERROR("000200", "Internal error"),

    /**
     * Arithmetic Errors
     */
    DIVISION_BY_ZERO("000300", "Division by zero"),
    NEGATIVE_SQUARE_ROOT("000300", "Square root is negative"),

    /**
     * Invalid Expression
     */
    INSUFFICIENT_PARAMETERS("000400", "operator %s (position: %d): insufficient parameters"),
    INVALID_TOKEN("000401", "token %s (position: %d): invalid token"),
    ;

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}



