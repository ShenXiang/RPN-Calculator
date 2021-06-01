package com.rpncalc.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Stack<T> {

    // Why not use java.util.Stack? Stack is a very old class. It is not good to extend the behaviors.
    //
    // Also JDK document says:
    // A more complete and consistent set of LIFO stack operations is provided
    // by the Deque interface and its implementations, which should be used in preference to this class.
    private Deque<T> values = new ArrayDeque<>();

    /**
     * Removes the object at the top of a stack and returns that object.
     *
     * @return object at the top
     */
    public T pop() {
        return values.removeLast();
    }

    /**
     * Pushes an item to the top of a stack
     *
     * @param value object value
     */
    public void push(T value) {
        values.addLast(value);
    }

    /**
     * Check stack is empty.
     */
    public boolean isEmpty() {
        return values.isEmpty();
    }

    /**
     * Returns the size of the stack
     */
    public int size() {
        return values.size();
    }

    /**
     * Return the iterator of the stack in case caller needs to traverse it
     */
    public Iterator<T> iterator() {
        return values.iterator();
    }
}
