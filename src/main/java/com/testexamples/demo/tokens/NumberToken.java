package com.testexamples.demo.tokens;

import lombok.Data;

@Data
public class NumberToken implements Token {
    private int number;

    public NumberToken (int number) {
        this.number = number;
    }
}