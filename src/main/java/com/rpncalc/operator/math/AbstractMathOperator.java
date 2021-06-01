package com.rpncalc.operator.math;

import com.rpncalc.exception.BusinessException;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.Operator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractMathOperator implements Operator {

    @Override
    public void execute() throws BusinessException {
        throw new BusinessException(ErrorCodeEnum.INTERNAL_ERROR);
    }
}
