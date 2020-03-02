package com.testexamples.demo.tokens;

import lombok.Data;

@Data
public abstract class LiteralToken implements Token {
    private char literal;
    LiteralToken (char literal) {
        this.literal = literal;
    }
}