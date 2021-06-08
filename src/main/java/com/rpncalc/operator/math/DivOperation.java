package com.rpncalc.operator.math;

import com.rpncalc.exception.CalculatorException;
import com.rpncalc.exception.ErrorCodeEnum;

import java.math.BigDecimal;
import java.util.function.Function;

public class DivOperation extends AbstractMathOperation {

    public static final String TOKEN = "/";

    public DivOperation(Function<BigDecimal[], BigDecimal> consumer) {
        super(consumer);
    }

    @Override
    public BigDecimal calculate(BigDecimal[] params) throws CalculatorException{
        if (BigDecimal.ZERO.compareTo(params[1]) == 0) {
            throw new CalculatorException(ErrorCodeEnum.DIVISION_BY_ZERO);
        }
        return function.apply(params);
    }


    @Override
    public String symbol() {
        return TOKEN;
    }

    @Override
    public int operandNum() {
        return 2;
    }
}
