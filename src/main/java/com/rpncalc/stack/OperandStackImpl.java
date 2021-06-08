package com.rpncalc.stack;

import com.rpncalc.snapshot.Memento;
import com.rpncalc.exception.CalculatorException;
import com.rpncalc.exception.ErrorCodeEnum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

public class OperandStackImpl implements OperandStack {

    private Deque<OperandValue> values = new ArrayDeque<>();

    @Override
    public Memento snapshot() {
        return new Memento(values);
    }

    @Override
    public void recover(Memento memento) {

        if (Objects.isNull(memento)) {
            return;
        }

        values.clear();
        values = new ArrayDeque<>(memento.getStack());
    }

    @Override
    public OperandValue[] pop(int count) throws CalculatorException {
        if (values.size() < count) {
            throw new CalculatorException(ErrorCodeEnum.INSUFFICIENT_PARAMETERS);
        }

        OperandValue[] result = new OperandValue[count];

        for (int i = count -1; i >= 0; --i) {
            result[i] = values.removeLast();
        }
        return result;
    }

    @Override
    public void push(OperandValue value) {
        values.addLast(value);
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        int i = 0;

        for (Iterator<OperandValue> iterator = values.iterator(); iterator.hasNext(); ++i) {
            str.append(iterator.next().toString());
            if (i < values.size() - 1) {
                str.append(" ");
            }
        }

        return str.toString();
    }
}
