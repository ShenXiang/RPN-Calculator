package com.rpncalc.snapshot;

import com.rpncalc.exception.CalculatorException;
import com.rpncalc.exception.ErrorCodeEnum;

import java.util.ArrayDeque;
import java.util.Deque;

public class Caretaker {

    private Deque<Memento> mementos = new ArrayDeque<>();

    public Caretaker add(Memento memento) {
        mementos.addLast(memento);
        return this;
    }

    public Caretaker undo() {
        if (mementos.isEmpty()) {
            return this;
        }

        mementos.removeLast();
        return this;
    }

    public Memento getMemento() {
        if (mementos.isEmpty()) {
            return Memento.emptyMemento();
        }
        return mementos.getLast();
    }
}
