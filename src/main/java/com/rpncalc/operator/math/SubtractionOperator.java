package com.rpncalc.operator.math;

import com.rpncalc.Constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SubtractionOperator extends AbstractMathOperator {

    @Override
    public String symbol() {
        return "-";
    }

    @Override
    public BigDecimal execute(BigDecimal... params) {
        return params[0].subtract(params[1]).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP);
    }
}
