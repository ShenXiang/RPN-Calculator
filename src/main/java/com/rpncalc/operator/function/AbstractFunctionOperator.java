package com.rpncalc.operator.function;

import com.rpncalc.exception.BusinessException;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.Operator;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public abstract class AbstractFunctionOperator implements Operator {

    @Override
    public BigDecimal execute(BigDecimal... params) throws BusinessException {
        throw new BusinessException(ErrorCodeEnum.INTERNAL_ERROR);
    }
}
