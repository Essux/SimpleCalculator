package com.testexamples.demo.tokens;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TokenConstants {
    private TokenConstants () {
    }

    private static final LiteralToken[] LITERAL_TOKENS = {
        new AddToken(),
        new SubstractToken(),
        new MultiplicationToken(),
        new DivisionToken()
    };
    public static List<LiteralToken> getLiteralTokens() {
        return Collections.unmodifiableList(Arrays.asList(LITERAL_TOKENS));
    }
    public static final char WHITESPACE = ' ';
}