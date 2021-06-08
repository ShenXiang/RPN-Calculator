package com.rpncalc.snapshot;

import java.util.ArrayDeque;
import java.util.Deque;

public class Caretaker {

    private Deque<Memento> mementos = new ArrayDeque<>();

    public void add(Memento memento) {
        mementos.addLast(memento);
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
