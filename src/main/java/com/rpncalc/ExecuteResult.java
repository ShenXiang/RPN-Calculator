package com.rpncalc;

import com.rpncalc.exception.ErrorCodeEnum;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

@Getter
public class ExecuteResult {

    private String result = "";

    private int tokenPos = 0;

    private ErrorCodeEnum error = ErrorCodeEnum.SUCCESS;

    private RpnExpression rpnExpression;

    private String token = "";

    private static final String IGNORED_TOKEN_REMAINED_FORMAT =
            "(the %s %s not pushed on to the stack due to the previous error)";

    public ExecuteResult(RpnExpression rpnExpression) {
        this.rpnExpression = rpnExpression;
    }

    public void setToken(String token, int tokenPos) {
        this.token = token;
        this.tokenPos = tokenPos;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setError(Integer error) {
        this.error = ErrorCodeEnum.valueOf(error);
    }

    public String getErrorMessage() {
        int originalCharPos = rpnExpression.getExpressionCharPos(tokenPos);

        if (error == ErrorCodeEnum.INSUFFICIENT_PARAMETERS || error == ErrorCodeEnum.INVALID_TOKEN) {
            return String.format(error.getMessage(), token, originalCharPos);

        } else {
            return error.getMessage();
        }
    }

    public void display() {
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

    public boolean isSuccess() {
        return ErrorCodeEnum.SUCCESS.equals(error);
    }
}
