package com.rpncalc.stack;

public interface OperandStack extends Restorable, Cloneable{

    /**
     * push a value to the top of the stack
     *
     */
    void push(OperandValue value);

    /**
     * pop n(count) elements from stack
     *
     * @return pop values in reserved way.
     */
    OperandValue[] pop(int count);

    /**
     * clear all elements
     */
    void clear();
}
