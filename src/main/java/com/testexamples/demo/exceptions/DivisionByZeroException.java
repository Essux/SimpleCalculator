package com.testexamples.demo.exceptions;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super(ExceptionMessages.DIVISION_BY_ZERO);
    }
}