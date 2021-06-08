package com.rpncalc;

import java.math.MathContext;

public class Constant {

    private Constant() {
    }
    public static final MathContext DECIMAL_STORE_PRECISION = new MathContext(15);

    public static final MathContext DECIMAL_DISPLAY_PRECISION = new MathContext(10);
}
