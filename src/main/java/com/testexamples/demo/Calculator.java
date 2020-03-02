package com.testexamples.demo;

import com.testexamples.demo.exceptions.DivisionByZeroException;

public class Calculator {
    public int sum(int a, int b) {
        return a+b;
    }

    public int multiply (int a, int b) {
        return a*b;
    }

    public int substract (int a, int b) {
        return a-b;
    }

    public int divide (int a, int b) throws DivisionByZeroException {
        if (b==0) throw new DivisionByZeroException();
        return a/b;
    }
}