package com.rpncalc;


import com.rpncalc.exception.ErrorCodeEnum;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ExecuteResult {
    private String errorMsg;

    private String errorCode;

    private String result = "";

    private Calculator calculator;

    private static final String IGNORED_TOKEN_REMAINED_FORMAT =
            "(the %s %s not pushed on to the stack due to the previous error)";

    public ExecuteResult(Calculator calculator) {
        this.calculator = calculator;
        this.errorCode = ErrorCodeEnum.SUCCESS.getCode();
    }

    public ExecuteResult(Calculator calculator, String errorMsg, String errorCode, String result){
        this(calculator);
        this.result = result;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public ExecuteResult(Calculator calculator, String result){
        this(calculator);
        this.result = result;
        this.errorCode = ErrorCodeEnum.SUCCESS.getCode();
    }

    public void display() {
        if (StringUtils.isNotBlank(errorMsg)) {
            System.out.println(errorMsg);
        }

        System.out.println("stack: " + result);

        String[] remainedTokens = calculator.getRemainedTokens();
        if (ArrayUtils.isEmpty(remainedTokens)) {
            return;
        }

        String str = String.join(",", remainedTokens);
        System.out.println(String.format(IGNORED_TOKEN_REMAINED_FORMAT, str, remainedTokens.length > 1 ? "were": "was"));
    }

    public boolean isSuccess() {
        return errorCode.equals(ErrorCodeEnum.SUCCESS.getCode());
    }
}
