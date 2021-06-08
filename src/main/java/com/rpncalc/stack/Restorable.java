package com.rpncalc.stack;

import com.rpncalc.snapshot.Memento;

public interface Restorable {
    /**
     * Undo with a memento instance
     */
    void recover(Memento memento);

    /**
     * Get the current snapshot
     */
    Memento snapshot();
}
