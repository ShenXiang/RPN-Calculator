package com.rpncalc.operator.math;

import com.rpncalc.Constant;
import com.rpncalc.exception.BusinessException;
import com.rpncalc.exception.ErrorCodeEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionOperator extends AbstractMathOperator {

    @Override
    public String symbol() {
        return "/";
    }

    @Override
    public BigDecimal execute(BigDecimal... params) throws BusinessException {

        if (BigDecimal.ZERO.compareTo(params[1]) == 0) {
            throw new BusinessException(ErrorCodeEnum.DIVISION_BY_ZERO);
        }

        // Take care, BigDecimal could cause Non-terminating decimal expansion ArithmeticException
        // So let's specify the scale value here.
        return params[0].divide(params[1], Constant.DECIMAL_STORE_PRECISION).setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP);
    }
}
