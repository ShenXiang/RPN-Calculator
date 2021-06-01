package com.rpncalc.stack;

import com.rpncalc.exception.BusinessException;

import java.math.BigDecimal;
import java.util.Iterator;

public class OperatorValueStack {

    private Stack<ValueElement> values = new Stack<>();

    private UndoLog<ValueElement> undoLog = new UndoLog<>();

    private int length = 0;

    public void startBatch() {
        length = 0;
    }

    public void endBatch() {
        undoLog.addLengthLog(length);
    }

    public BigDecimal pop() {
        ValueElement stackValue = values.pop();
        undoLog.addPopLog(stackValue);
        ++length;
        return stackValue.getValue();
    }

    public void push(BigDecimal value) {
        ValueElement stackValue = new ValueElement(value);
        values.push(stackValue);
        undoLog.addPushLog(stackValue);
        ++length;
    }

    public int size() {
        return values.size();
    }

    public void clear() {
        startBatch();
        // In order to store the clear log, we don't use stack.clear here
        while (!values.isEmpty()) {
            pop();
        }
        endBatch();
    }

    public void undo() throws BusinessException {
        undoLog.undo(values);
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        int i = 0;

        for (Iterator<ValueElement> iterator = values.iterator(); iterator.hasNext(); ++i) {
            str.append(iterator.next().toString());
            if (i < values.size() - 1) {
                str.append(" ");
            }
        }

        return str.toString();
    }

}
