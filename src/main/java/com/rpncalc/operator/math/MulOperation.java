package com.rpncalc.operator.math;

import java.math.BigDecimal;
import java.util.function.Function;

public class MulOperation extends AbstractMathOperation {

    public static final String TOKEN = "*";

    public MulOperation(Function<BigDecimal[], BigDecimal> consumer) {
        super(consumer);
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
