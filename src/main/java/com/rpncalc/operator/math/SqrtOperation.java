package com.rpncalc.operator.math;

import com.rpncalc.Constant;
import com.rpncalc.exception.CalculatorException;
import com.rpncalc.exception.ErrorCodeEnum;

import java.math.BigDecimal;
import java.util.function.Function;

public class SqrtOperation extends AbstractMathOperation {

    public static final String TOKEN = "sqrt";

    public SqrtOperation(Function<BigDecimal[], BigDecimal> consumer) {
        super(consumer);
    }

    @Override
    public String symbol() {
        return TOKEN;
    }

    @Override
    public BigDecimal calculate(BigDecimal... params) throws CalculatorException {
        int compareZero = params[0].compareTo(BigDecimal.ZERO);

        if (compareZero < 0) {
            throw new CalculatorException(ErrorCodeEnum.NEGATIVE_SQUARE_ROOT);
        }

        if (compareZero == 0) {
            return BigDecimal.ZERO;
        }

        return params[0].sqrt(Constant.DECIMAL_STORE_PRECISION);
    }

    @Override
    public int operandNum() {
        return 1;
    }
}
