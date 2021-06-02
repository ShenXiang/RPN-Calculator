package com.rpncalc;

import com.rpncalc.exception.BusinessException;
import com.rpncalc.exception.ErrorCodeEnum;
import com.rpncalc.operator.function.ClearFunctionOperator;
import com.rpncalc.operator.Operator;
import com.rpncalc.operator.function.UndoFunctionOperator;
import com.rpncalc.operator.math.AdditionOperator;
import com.rpncalc.operator.math.DivisionOperator;
import com.rpncalc.operator.math.MultiplicationOperator;
import com.rpncalc.operator.OperatorTypeEnum;
import com.rpncalc.operator.math.SqrtOperator;
import com.rpncalc.operator.math.SubtractionOperator;
import com.rpncalc.stack.OperatorValueStack;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private OperatorValueStack stack = new OperatorValueStack();

    private Map<String, Operator> operatorMap;

    private String[] tokens;
    private int tokenCursor;
    private String express;

    public Calculator() {
        tokens = new String[0];
        tokenCursor = 0;
        express = "";

        initOperatorMap();
    }

    private void initOperatorMap() {
        operatorMap = new HashMap<>();

        Operator[] ops = new Operator[]{
                new AdditionOperator(),
                new SubtractionOperator(),
                new MultiplicationOperator(),
                new DivisionOperator(),
                new SqrtOperator(),
                new ClearFunctionOperator(stack),
                new UndoFunctionOperator(stack),
        };

        for (Operator op : ops) {
            operatorMap.put(op.symbol(), op);
        }
    }

    private int getOriginalTokenPos() {

        int tokenIndex = 0;
        int pos = 0;

        while (pos < express.length()) {

            if (express.charAt(pos) != ' ') {
                if (tokenIndex == tokenCursor) {
                    return pos + 1;
                }
                ++tokenIndex;
                while (pos < express.length() && express.charAt(pos) != ' '){
                    ++pos;
                }
            }
            ++pos;
        }

        return -1;
    }


    private void processOperator(Operator operator, OperatorTypeEnum type) throws BusinessException {

        int paramNum = type.getParamNum();
        if (stack.size() < paramNum) {
            String message = String.format(
                    ErrorCodeEnum.INSUFFICIENT_PARAMETERS.getMessage(), tokens[tokenCursor], getOriginalTokenPos());

            throw new BusinessException(ErrorCodeEnum.INSUFFICIENT_PARAMETERS, message);
        }

        switch (paramNum) {
            case 0:
                operator.execute();
                break;

            case 1:
                BigDecimal input = stack.pop();
                stack.push(operator.execute(input));
                break;

            case 2:
                stack.startBatch();

                // The top element is right
                BigDecimal right = stack.pop();
                BigDecimal left = stack.pop();

                stack.push(operator.execute(left, right));
                stack.endBatch();
                break;

            default:
                throw new BusinessException(
                        ErrorCodeEnum.INTERNAL_ERROR, "Unsupported operator:" + type.getSymbol());
        }
    }

    private void processToken(String token) throws BusinessException {

        if (operatorMap.containsKey(token)) {
            Operator operator = operatorMap.get(token);
            OperatorTypeEnum type = OperatorTypeEnum.fromSymbol(token);
            processOperator(operator, type);
            return;
        }

        try {
            stack.push(new BigDecimal(token, Constant.DECIMAL_STORE_PRECISION));

        } catch (NumberFormatException e) {
            throw new BusinessException(
                    ErrorCodeEnum.INVALID_TOKEN,
                    String.format(ErrorCodeEnum.INVALID_TOKEN.getMessage(), token, getOriginalTokenPos()));
        }
    }

    /**
     * This function is used to display the remained tokens that are ignored because of previous token error.
     *
     * @return The remained tokens that were cut off
     */
    public String[] getRemainedTokens() {
        if (tokenCursor != tokens.length) {
            return ArrayUtils.subarray(tokens, tokenCursor + 1, tokens.length);
        }

        return new String[0];
    }

    /**
     * Execute the input RPN expression
     *
     * @param expression RPN expression
     * @return object contains result and error message.
     */
    public ExecuteResult execute(String expression) {
        if (StringUtils.isBlank(expression)) {
            return new ExecuteResult(this);
        }

        tokens = expression.trim().split("\\s+");
        tokenCursor = 0;
        express = expression;

        try {
            for (String token : tokens) {
                processToken(token);
                ++tokenCursor;
            }

        } catch (BusinessException e) {
            return new ExecuteResult(this, e.getMessage(), e.getCode(), stack.toString());
        }

        return new ExecuteResult(this, stack.toString());
    }
}
