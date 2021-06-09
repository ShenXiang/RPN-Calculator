package com.rpncalc;

import com.rpncalc.exception.CalculatorException;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.Operation;
import com.rpncalc.operator.OperationFactory;
import com.rpncalc.operator.OperationFactoryImpl;
import com.rpncalc.operator.function.UndoOperation;
import com.rpncalc.snapshot.Caretaker;
import com.rpncalc.snapshot.Memento;
import com.rpncalc.stack.OperandStack;
import com.rpncalc.stack.OperandStackImpl;
import com.rpncalc.stack.OperandValue;

import java.math.BigDecimal;
import java.util.Arrays;

public class Calculator {

    private OperandStack operandStack;

    private Caretaker caretaker;

    private OperationFactory operationFactory;

    public Calculator() {
        this.operandStack = new OperandStackImpl();
        this.caretaker = new Caretaker();
        this.operationFactory = new OperationFactoryImpl(operandStack, caretaker);
    }

    private void processOperator(Operation operation) {

        int operandNum = operation.operandNum();

        if (operandNum == 0) {
            // Execute a function operation
            operation.doFunction();
        }

        else {
            // Execute a math operation
            BigDecimal[] operands = Arrays
                    .stream(operandStack.pop(operandNum))
                    .map(OperandValue::getValue).toArray(BigDecimal[]::new);

            BigDecimal resultVal = operation.calculate(operands);

            operandStack.push(new OperandValue(resultVal));
        }
    }

    private void processNumber(String token) {
        try {
            BigDecimal value = new BigDecimal(token);
            operandStack.push(new OperandValue(value));

        } catch (NumberFormatException e) {
            throw new CalculatorException(ErrorCodeEnum.INVALID_TOKEN);
        }
    }

    private void processToken(String token) {

        Operation operation = operationFactory.create(token);
        if (operation != null) {
            processOperator(operation);

        } else {
            processNumber(token);
        }

        // Take snapshot
        if (!UndoOperation.TOKEN.equals(token)) {
            // undo token doesn't need to take snapshot
            Memento memento = operandStack.snapshot();
            caretaker.add(memento);
        }
    }

    /**
     * Execute the input RPN expression
     *
     * @param rpnExpression RPN expression
     * @return object contains result and error message.
     */
    public ExecuteResult execute(String rpnExpression) {

        RpnExpression expression = new RpnExpression(rpnExpression);
        String[] tokens = expression.getTokens();

        ExecuteResult result = new ExecuteResult(expression);

        for(int pos = 0; pos < tokens.length; ++pos) {

            try {
                result.setTokenPos(pos);
                processToken(tokens[pos]);

            } catch (CalculatorException e) {
                result.setError(e.getCode());
                return result;

            } finally {
                result.setResult(operandStack.toString());
            }
        }

        return result;
    }

}
