package com.rpncalc.stack;

import com.rpncalc.exception.CalculatorException;

public interface OperandStack extends Restorable, Cloneable{

    /**
     * push a value to the top of the stack
     *
     */
    void push(OperandValue value);

    /**
     * pop n(count) elements from stack
     *
     * @return poped values in reserved way.
     * @throws CalculatorException no enough elements
     */
    OperandValue[] pop(int count) throws CalculatorException;

    /**
     * clear all elements
     */
    void clear();
}
