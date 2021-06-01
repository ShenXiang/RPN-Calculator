package com.rpncalc.operator.function;

import com.rpncalc.stack.OperatorValueStack;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClearFunctionOperator extends AbstractFunctionOperator {

    private OperatorValueStack stack;

    @Override
    public String symbol() {
        return "clear";
    }

    @Override
    public void execute() {
        stack.clear();
    }
}
