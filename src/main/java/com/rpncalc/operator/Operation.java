package com.rpncalc.operator;

import com.rpncalc.exception.CalculatorException;

import java.math.BigDecimal;
import java.util.function.Consumer;

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
     * @throws CalculatorException common exception
     */
    BigDecimal calculate(BigDecimal[] params) throws CalculatorException;

    /**
     * Execute no args operations without return type
     *
     * @throws CalculatorException common exception
     */
    void doFunction() throws CalculatorException;
}
