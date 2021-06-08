package com.rpncalc.snapshot;

import com.rpncalc.stack.OperandValue;

import java.util.ArrayDeque;
import java.util.Deque;

public class Memento {

    private Deque<OperandValue> stack;

    public Memento(Deque<OperandValue> stack) {
        this.stack = new ArrayDeque<>(stack);
    }

    /**
     * @return the stack stores in memento.
     */
    public Deque<OperandValue> getStack() {
        return stack;
    }

    public static Memento emptyMemento() {
        return new Memento(new ArrayDeque<>());
    }
}
