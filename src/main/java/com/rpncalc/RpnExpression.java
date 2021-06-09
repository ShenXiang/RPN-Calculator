package com.rpncalc;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class RpnExpression {

    private String[] tokens;
    private String expression;

    public RpnExpression(String rpn) {

        if (StringUtils.isBlank(rpn)) {
            tokens = new String[0];
            this.expression = "";

        } else {
            tokens = rpn.trim().split("\\s+");
            this.expression = rpn;
        }
    }

    /**
     * Get all tokens.
     */
    public String[] getTokens() {
        return tokens;
    }

    /**
     * Get the remained tokens from input position.
     */
    public String[] getTokens(int tokenPos) {
        if (tokenPos != tokens.length) {
            return ArrayUtils.subarray(tokens, tokenPos + 1, tokens.length);
        }

        return new String[0];
    }

    /**
     * Calculate to the char pos in the original expression
     */
    public int getExpressionCharPos(int tokenPos) {

        int tokenIndex = 0;
        int pos = 0;

        while (pos < expression.length()) {
            while (pos < expression.length() && expression.charAt(pos) == ' ') {
                ++pos;
            }
            if (tokenIndex == tokenPos) {
                return pos + 1;
            }
            ++tokenIndex;

            while (pos < expression.length() && expression.charAt(pos) != ' ') {
                ++pos;
            }
        }

        return -1;
    }
}
