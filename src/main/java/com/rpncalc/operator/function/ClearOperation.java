package com.rpncalc.operator.function;

import com.rpncalc.stack.OperandStack;

public class ClearOperation extends AbstractFuncOperation {

    public static final String TOKEN = "clear";

    private OperandStack stack;

    public ClearOperation(OperandStack stack) {
        this.stack = stack;
    }

    @Override
    public String symbol() {
        return TOKEN;
    }

    @Override
    public void doFunction() {
        stack.clear();
    }
}
