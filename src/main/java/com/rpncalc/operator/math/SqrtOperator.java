package com.rpncalc.operator.math;

import com.rpncalc.Constant;
import com.rpncalc.exception.BusinessException;
import com.rpncalc.exception.ErrorCodeEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SqrtOperator extends AbstractMathOperator {

    @Override
    public String symbol() {
        return "sqrt";
    }

    @Override
    public BigDecimal execute(BigDecimal... params) throws BusinessException {
        int compareZero = params[0].compareTo(BigDecimal.ZERO);

        if (compareZero < 0) {
            throw new BusinessException(ErrorCodeEnum.NEGATIVE_SQUARE_ROOT);
        }

        if (compareZero == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal result = BigDecimal.valueOf(Math.sqrt(params[0].doubleValue()));
        return result.setScale(
                Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                RoundingMode.HALF_UP);
    }
}
