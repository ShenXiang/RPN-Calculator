package com.rpncalc.operator;

import java.math.BigDecimal;

public interface Operation {

    /**
     * Return the symbol name of a operator: + - * / sqrt clear undo
     */
    String symbol();

    /**
     * Return the operand count
     */
    int operandNum();

    /**
     * Execute operation
     *
     * @param params varying parameter list
     * @return operator return value
     */
    BigDecimal calculate(BigDecimal[] params);

    /**
     * Execute no args operations without return type
     */
    void doFunction();
}
