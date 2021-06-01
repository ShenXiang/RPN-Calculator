package com.rpncalc.operator;

import com.rpncalc.exception.BusinessException;

import java.math.BigDecimal;

public interface Operator {

    /**
     * Return the symbol name of a operator: + - * / sqrt clear undo
     */
    String symbol();

    /**
     * Execute operation
     *
     * @param params varying parameter list
     * @return operator return value
     * @throws BusinessException common exception
     */
    BigDecimal execute(BigDecimal... params) throws BusinessException;

    /**
     * Execute no args operations without return type
     *
     * @throws BusinessException common exception
     */
    void execute() throws BusinessException;
}
