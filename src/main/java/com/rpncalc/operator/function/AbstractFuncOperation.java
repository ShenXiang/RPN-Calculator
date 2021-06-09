package com.rpncalc.operator.function;

import com.rpncalc.exception.CalculatorException;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.Operation;

import java.math.BigDecimal;

public abstract class AbstractFuncOperation implements Operation {

    @Override
    public BigDecimal calculate(BigDecimal[] params) {
        throw new CalculatorException(ErrorCodeEnum.INTERNAL_ERROR);
    }

    @Override
    public int operandNum() {
        return 0;
    }
}
