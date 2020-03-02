package com.testexamples.demo.exceptions;

public class UnknownTokenException extends Exception {
    public UnknownTokenException() {
        super(ExceptionMessages.UNEXPECTED_TOKEN);
    }
}