package com.rpncalc.operator;

import com.rpncalc.Constant;
import com.rpncalc.operator.function.ClearOperation;
import com.rpncalc.operator.function.UndoOperation;
import com.rpncalc.operator.math.AddOperation;
import com.rpncalc.operator.math.DivOperation;
import com.rpncalc.operator.math.MulOperation;
import com.rpncalc.operator.math.SqrtOperation;
import com.rpncalc.operator.math.SubOperation;
import com.rpncalc.snapshot.Caretaker;
import com.rpncalc.stack.OperandStack;

import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class OperationFactoryImpl implements OperationFactory {

    private Map<String, Operation> operatorMap;

    public OperationFactoryImpl(OperandStack stack, Caretaker caretaker) {

        operatorMap = new HashMap<>();

        Operation[] ops = new Operation[]{
                new AddOperation(params -> params[0].add(params[1])),
                new SubOperation(params -> params[0].subtract(params[1])),
                new MulOperation(params -> params[0].multiply(params[1])),

                new DivOperation(params -> {
                    // Take care, BigDecimal could cause
                    // Non-terminating decimal expansion ArithmeticException
                    // So let's specify the scale value here.
                    return params[0].divide(params[1], Constant.DECIMAL_STORE_PRECISION).setScale(
                            Constant.DECIMAL_STORE_PRECISION.getPrecision(),
                            RoundingMode.HALF_UP
                    );
                }),

                new SqrtOperation(params -> params[0].sqrt(Constant.DECIMAL_STORE_PRECISION)),

                new ClearOperation(stack),
                new UndoOperation(stack, caretaker)
        };

        for (Operation op : ops) {
            operatorMap.put(op.symbol(), op);
        }
    }

    @Override
    public Operation create(String operator) {
        return operatorMap.get(operator);
    }
}
