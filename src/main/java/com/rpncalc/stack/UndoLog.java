package com.rpncalc.stack;

import com.rpncalc.exception.BusinessException;
import com.rpncalc.exception.ErrorCodeEnum;

/**
 * UndoLog records all stack changes by an appending way.
 * It is used to undo the previous operations.
 *
 * @author Xiang Shen
 */
public class UndoLog<T> {

    private Stack<UndoLogElement> logElementStack = new Stack<>();

    /**
     * Add the push operation to the undo log
     *
     * @param value push value
     */
    public void addPushLog(T value) {
        logElementStack.push(new UndoLogElement(value, UndoLogElementType.PUSH));
    }

    /**
     * Add the pop operation to the undo log
     *
     * @param value pop value
     */
    public void addPopLog(T value) {
        logElementStack.push(new UndoLogElement(value, UndoLogElementType.POP));
    }

    /**
     * Add the total length of batch operations to the undo log
     *
     * @param length total size of the operation numbers
     */
    public void addLengthLog(int length) {
        logElementStack.push(new UndoLogElement(length, UndoLogElementType.LENGTH));
    }


    /**
     * Use undo log stack to restore the operations
     *
     * @param originalValues original values
     * @throws BusinessException common exception
     */
    @SuppressWarnings("unchecked")
    public void undo(Stack originalValues) throws BusinessException {

        if (logElementStack.isEmpty()) {
            return;
        }

        UndoLogElement logElement = logElementStack.pop();

        switch (logElement.getType()) {
            case PUSH:
                originalValues.pop();
                break;
            case POP:
                Object val = logElement.getValue();

                originalValues.push(val);
                break;

            case LENGTH:
                int length = (int)logElement.getValue();
                for (int i = 0; i <length; ++i) {
                    undo(originalValues);
                }
                break;

            default:
                throw new BusinessException(ErrorCodeEnum.INTERNAL_ERROR, "Unknown operator");
        }
    }
}
