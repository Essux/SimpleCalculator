package com.testexamples.demo;

import java.util.ArrayList;
import java.util.List;

import com.testexamples.demo.exceptions.ExceptionMessages;
import com.testexamples.demo.exceptions.InvalidExpressionException;
import com.testexamples.demo.tokens.LiteralToken;
import com.testexamples.demo.tokens.NumberToken;
import com.testexamples.demo.tokens.Token;
import com.testexamples.demo.tokens.TokenConstants;

class Tokenizer {

    public List<Token> tokenize (String input) throws InvalidExpressionException {
        List<Token> tokens = new ArrayList<>();

        int index = 0;
        while (index < input.length()) {
            char currentChar = input.charAt(index);

            if (currentChar==TokenConstants.WHITESPACE) {
                index++;
            }
            else if (Character.isDigit(currentChar)) {
                int number = 0;

                while (index < input.length() && Character.isDigit(currentChar = input.charAt(index))) {
                    number *= 10;
                    number += Character.getNumericValue(currentChar);
                    index++;
                }
                tokens.add(new NumberToken(number));
            }
            else {
                LiteralToken operatorToken = getLiteralToken(currentChar);
                tokens.add(operatorToken);
                index++;
            }
        }
        return tokens;
    }

    private LiteralToken getLiteralToken(char currentChar) throws InvalidExpressionException {
        for (LiteralToken literalToken : TokenConstants.getLiteralTokens()) {
            if (currentChar == literalToken.getLiteral()) {
                return literalToken;
            }
        }
        throw new InvalidExpressionException(ExceptionMessages.UNEXPECTED_CHARACTER);
    }

}