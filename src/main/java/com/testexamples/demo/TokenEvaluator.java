package com.testexamples.demo;

import java.util.List;

import com.testexamples.demo.exceptions.DivisionByZeroException;
import com.testexamples.demo.exceptions.ExceptionMessages;
import com.testexamples.demo.exceptions.InvalidExpressionException;
import com.testexamples.demo.exceptions.UnknownTokenException;
import com.testexamples.demo.tokens.AddToken;
import com.testexamples.demo.tokens.DivisionToken;
import com.testexamples.demo.tokens.MultiplicationToken;
import com.testexamples.demo.tokens.NumberToken;
import com.testexamples.demo.tokens.SubstractToken;
import com.testexamples.demo.tokens.Token;

class TokenEvaluator {
    private Calculator calculator;

    public TokenEvaluator() {
        calculator = new Calculator();
    }

    public int evaluateTokens (List<Token> tokens) throws DivisionByZeroException, InvalidExpressionException,
            UnknownTokenException {
        int currentValue = 0;
        Token lastOperatorToken = null;

        if (tokens.isEmpty()) throw new InvalidExpressionException(ExceptionMessages.EXPRESSION_EMPTY_MESSAGE);

        for (int i = 0; i < tokens.size(); i++) {
            Token currentToken = tokens.get(i);
            if (i%2==0) {
                if (!(currentToken instanceof NumberToken)) {
                    throw new InvalidExpressionException(ExceptionMessages.EXPECTED_NUMBER);
                }
                NumberToken numberToken = (NumberToken) currentToken;
                if (i==0) {
                    currentValue = numberToken.getNumber();
                }
                else {
                    currentValue = performOperationWithTokens(currentValue, numberToken, lastOperatorToken);
                }
            }
            else {
                if (currentToken instanceof NumberToken) {
                    throw new InvalidExpressionException(ExceptionMessages.EXPECTED_OPERATOR);
                }
                lastOperatorToken = currentToken;
            }
        }

        return currentValue;
    }

    private int performOperationWithTokens (int currentValue, NumberToken numberToken, Token lastOperatorToken)
            throws DivisionByZeroException, UnknownTokenException {
        if (lastOperatorToken instanceof AddToken) {
            return calculator.sum(currentValue, numberToken.getNumber());
        }
        else if (lastOperatorToken instanceof SubstractToken) {
            return calculator.substract(currentValue, numberToken.getNumber());
        }
        else if (lastOperatorToken instanceof MultiplicationToken) {
            return calculator.multiply(currentValue, numberToken.getNumber());
        }
        else if (lastOperatorToken instanceof DivisionToken) {
            return calculator.divide(currentValue, numberToken.getNumber());
        }
        else {
            throw new UnknownTokenException();
        }
    }
}