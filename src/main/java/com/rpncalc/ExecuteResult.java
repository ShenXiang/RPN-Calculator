package com.rpncalc;

import com.rpncalc.exception.ErrorCodeEnum;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Objects;

public class ExecuteResult {

    @Getter
    private String result = "";

    @Getter
    private ErrorCodeEnum error = ErrorCodeEnum.SUCCESS;

    private int tokenPos = 0;

    private RpnExpression rpnExpression;

    private static final String IGNORED_TOKEN_REMAINED_FORMAT =
            "(the %s %s not pushed on to the stack due to the previous error)";

    public ExecuteResult(RpnExpression rpnExpression) {
        this.rpnExpression = rpnExpression;
    }

    public void setTokenPos(int tokenPos) {
        this.tokenPos = tokenPos;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setError(Integer error) {
        this.error = ErrorCodeEnum.valueOf(error);
    }

    public String getErrorMessage() {
        if (Objects.isNull(rpnExpression)) {
            return "";
        }

        int originalCharPos = rpnExpression.getExpressionCharPos(tokenPos);

        if (error == ErrorCodeEnum.INSUFFICIENT_PARAMETERS || error == ErrorCodeEnum.INVALID_TOKEN) {

            String[] tokens = rpnExpression.getTokens();
            String token = "";
            if (tokenPos < tokens.length) {
                token = tokens[tokenPos];
            }

            return String.format(error.getMessage(), token, originalCharPos);

        } else {
            return error.getMessage();
        }
    }

    /**
     * Print error message, current stack elements and unresolved tokens to console.
     */
    public void print() {
        if (!isSuccess()) {
            System.out.println(getErrorMessage());
        }

        System.out.println("stack: " + result);

        String[] remainedTokens = rpnExpression.getTokens(tokenPos);
        if (ArrayUtils.isEmpty(remainedTokens)) {
            return;
        }

        String str = String.join(",", remainedTokens);
        System.out.println(String.format(IGNORED_TOKEN_REMAINED_FORMAT, str, remainedTokens.length > 1 ? "were": "was"));
    }

    private boolean isSuccess() {
        return ErrorCodeEnum.SUCCESS.equals(error);
    }
}
