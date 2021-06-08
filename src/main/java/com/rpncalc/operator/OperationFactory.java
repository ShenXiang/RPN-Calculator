package com.rpncalc.operator;

public interface OperationFactory {
    /**
     * Get Operation object from name
     */
    Operation create(String operation);
}