package com.testexamples.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import com.testexamples.demo.exceptions.DivisionByZeroException;
import com.testexamples.demo.exceptions.InvalidExpressionException;
import com.testexamples.demo.exceptions.UnknownTokenException;
import com.testexamples.demo.tokens.AddToken;
import com.testexamples.demo.tokens.DivisionToken;
import com.testexamples.demo.tokens.MultiplicationToken;
import com.testexamples.demo.tokens.NumberToken;
import com.testexamples.demo.tokens.SubstractToken;
import com.testexamples.demo.tokens.Token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class TokenEvaluatorTest {

    @InjectMocks
    TokenEvaluator tokenEvaluator;

    @InjectMocks
    List<Token> inputTokens = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void evaluatesSingleInteger()
            throws DivisionByZeroException, InvalidExpressionException, UnknownTokenException {
        inputTokens.add(new NumberToken(56));

        assertEquals(56, tokenEvaluator.evaluateTokens(inputTokens));
    }

    @Test
    public void evaluatesSingleSum() throws DivisionByZeroException, InvalidExpressionException, UnknownTokenException {
        inputTokens.add(new NumberToken(5));
        inputTokens.add(new AddToken());
        inputTokens.add(new NumberToken(13));

        assertEquals(18, tokenEvaluator.evaluateTokens(inputTokens));
    }

    @Test
    public void evaluatesMultipleSums() throws DivisionByZeroException, InvalidExpressionException,
            UnknownTokenException {
        inputTokens.add(new NumberToken(200));
        inputTokens.add(new AddToken());
        inputTokens.add(new NumberToken(450));
        inputTokens.add(new AddToken());
        inputTokens.add(new NumberToken(100));
        inputTokens.add(new AddToken());
        inputTokens.add(new NumberToken(200));

        assertEquals(950, tokenEvaluator.evaluateTokens(inputTokens));
    }

    @Test
    public void evaluatesSingleSubstraction() throws DivisionByZeroException, InvalidExpressionException,
            UnknownTokenException {
        inputTokens.add(new NumberToken(56));
        inputTokens.add(new SubstractToken());
        inputTokens.add(new NumberToken(60));

        assertEquals(-4, tokenEvaluator.evaluateTokens(inputTokens));
    }

    @Test
    public void evaluatesSingleMultiplication() throws DivisionByZeroException, InvalidExpressionException,
            UnknownTokenException {
        inputTokens.add(new NumberToken(8));
        inputTokens.add(new MultiplicationToken());
        inputTokens.add(new NumberToken(9));

        assertEquals(72, tokenEvaluator.evaluateTokens(inputTokens));
    }

    
    @Test
    public void evaluatesMultipleMultiplications() throws DivisionByZeroException, InvalidExpressionException,
            UnknownTokenException {
        inputTokens.add(new NumberToken(9));
        inputTokens.add(new MultiplicationToken());
        inputTokens.add(new NumberToken(32));
        inputTokens.add(new MultiplicationToken());
        inputTokens.add(new NumberToken(172));
        inputTokens.add(new MultiplicationToken());
        inputTokens.add(new NumberToken(800));

        assertEquals(39628800, tokenEvaluator.evaluateTokens(inputTokens));
    }

    @Test
    public void evaluatesMultipleDivisions() throws DivisionByZeroException, InvalidExpressionException,
            UnknownTokenException {
        inputTokens.add(new NumberToken(47232823));
        inputTokens.add(new DivisionToken());
        inputTokens.add(new NumberToken(28));
        inputTokens.add(new DivisionToken());
        inputTokens.add(new NumberToken(48));
        inputTokens.add(new DivisionToken());
        inputTokens.add(new NumberToken(73));

        assertEquals(481, tokenEvaluator.evaluateTokens(inputTokens));
    }

    @Test
    public void throwsExceptionDivisionByZeroException() {
        inputTokens.add(new NumberToken(345));
        inputTokens.add(new DivisionToken());
        inputTokens.add(new NumberToken(8));
        inputTokens.add(new DivisionToken());
        inputTokens.add(new NumberToken(0));
        
        assertThrows(DivisionByZeroException.class, () -> tokenEvaluator.evaluateTokens(inputTokens));
    }

    @Test
    public void throwsExceptionWithTwoConsecutivePlus() {
        inputTokens.add(new NumberToken(77));
        inputTokens.add(new AddToken());
        inputTokens.add(new AddToken());
        
        assertThrows(InvalidExpressionException.class, () -> tokenEvaluator.evaluateTokens(inputTokens));
    }

    @Test
    public void throwsExceptionWithTwoConsecutiveNumbers() {
        inputTokens.add(new NumberToken(104));
        inputTokens.add(new NumberToken(35));
        inputTokens.add(new AddToken());
        
        assertThrows(InvalidExpressionException.class, () -> tokenEvaluator.evaluateTokens(inputTokens));
    }

    @Test
    public void throwsExceptionWithNoTokens() {
        assertThrows(InvalidExpressionException.class, () -> tokenEvaluator.evaluateTokens(inputTokens));
    }

}