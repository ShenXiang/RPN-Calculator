package com.rpncalc.stack;

public enum UndoLogElementType {
    PUSH,
    POP,
    /**
     * Records the affected next elements for one operations.
     * For example, the length for operator "+" is 2
     */
    LENGTH,
    ;
}
