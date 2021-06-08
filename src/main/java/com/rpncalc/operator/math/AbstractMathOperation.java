package com.rpncalc.operator.math;

import com.rpncalc.exception.CalculatorException;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.Operation;

import java.math.BigDecimal;
import java.util.function.Function;

public abstract class AbstractMathOperation implements Operation {

    protected Function<BigDecimal[], BigDecimal> function;

    public AbstractMathOperation(Function<BigDecimal[], BigDecimal> function) {
        this.function = function;
    }

    @Override
    public void doFunction() {
        throw new CalculatorException(ErrorCodeEnum.INTERNAL_ERROR);
    }

    @Override
    public BigDecimal calculate(BigDecimal[] params) {
        return function.apply(params);
    }

}
