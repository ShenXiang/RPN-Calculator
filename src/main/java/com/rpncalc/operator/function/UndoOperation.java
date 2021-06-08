package com.rpncalc.operator.function;

import com.rpncalc.snapshot.Caretaker;
import com.rpncalc.stack.OperandStack;

public class UndoOperation extends AbstractOperation {

    public static final String TOKEN = "undo";

    private OperandStack stack;

    private Caretaker caretaker;

    public UndoOperation(OperandStack stack, Caretaker caretaker) {
       this.stack = stack;
       this.caretaker = caretaker;
    }

    @Override
    public  String symbol() {
        return TOKEN;
    }

    @Override
    public void doFunction() {
        stack.recover(caretaker.undo().getMemento());
    }
}
