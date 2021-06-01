package com.rpncalc.operator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum OperatorTypeEnum {

    /**
     * Unsupported Operators
     */
    UNSUPPORTED("", 0),

    /**
     * Binary Operators
     */
    ADDITION("+", 2),
    SUBTRACTION("-", 2),
    MULTIPLICATION("*", 2),
    DIVISION("/",2),

    /**
     * Unary Operators
     */
    SQRT("sqrt", 1),

    /**
     * Function Operators
     */
    CLEAR("clear", 0),
    UNDO("undo", 0)
    ;

    private String symbol;

    private int paramNum;

    public static OperatorTypeEnum fromSymbol(String symbol) {
        OperatorTypeEnum[] es = OperatorTypeEnum.values();
        return Arrays
                .stream(es)
                .filter(e -> e.symbol.equals(symbol))
                .findAny()
                .orElse(OperatorTypeEnum.UNSUPPORTED);
    }
}
