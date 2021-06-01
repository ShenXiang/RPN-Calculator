package com.rpncalc.operator.function;

import com.rpncalc.stack.OperatorValueStack;
import com.rpncalc.exception.BusinessException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UndoFunctionOperator extends AbstractFunctionOperator {
    private OperatorValueStack stack;

    @Override
    public String symbol() {
        return "undo";
    }

    @Override
    public void execute() throws BusinessException {
        stack.undo();
    }
}
