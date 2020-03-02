package com.testexamples.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import com.testexamples.demo.exceptions.InvalidExpressionException;
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

class TokenizerTest {

    @InjectMocks
    Tokenizer tokenizer;

    @InjectMocks
    List<Token> expectedTokens = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void tokenizesSingleInteger() throws InvalidExpressionException {
        expectedTokens.add(new NumberToken(15));
        assertEquals(expectedTokens, tokenizer.tokenize("15"));
    }

    @Test
    public void tokenizesSingleIntegerWithWhitespace() throws InvalidExpressionException {
        expectedTokens.add(new NumberToken(3));
        assertEquals(expectedTokens, tokenizer.tokenize("      3     "));
    }

    @Test
    public void tokenizesEmptyString() throws InvalidExpressionException {
        assertEquals(expectedTokens, tokenizer.tokenize(""));
    }

    
    @Test
    public void tokenizesWhitespaceString() throws InvalidExpressionException {
        assertEquals(expectedTokens, tokenizer.tokenize("       "));
    }

    @Test
    public void tokenizesTwoOperandSum() throws InvalidExpressionException {
        expectedTokens.add(new NumberToken(15));
        expectedTokens.add(new AddToken());
        expectedTokens.add(new NumberToken(7));

        assertEquals(expectedTokens, tokenizer.tokenize("15 + 7"));
    }

    @Test
    public void tokenizesMultipleIntegers() throws InvalidExpressionException {
        expectedTokens.add(new NumberToken(8));
        expectedTokens.add(new NumberToken(3));
        expectedTokens.add(new NumberToken(29));

        assertEquals(expectedTokens, tokenizer.tokenize("8 3 29"));
    }

    @Test
    public void tokenizesMultipleOperators() throws InvalidExpressionException {
        expectedTokens.add(new SubstractToken());
        expectedTokens.add(new AddToken());
        expectedTokens.add(new MultiplicationToken());

        assertEquals(expectedTokens, tokenizer.tokenize("- + *"));
    }

    @Test
    public void tokenizesMultipleConsecutiveOperators() throws InvalidExpressionException {
        expectedTokens.add(new SubstractToken());
        expectedTokens.add(new DivisionToken());
        expectedTokens.add(new MultiplicationToken());

        assertEquals(expectedTokens, tokenizer.tokenize("-/*"));
    }

    @Test
    public void tokenizesMultipleOperations() throws InvalidExpressionException {
        expectedTokens.add(new NumberToken(3));
        expectedTokens.add(new AddToken());
        expectedTokens.add(new NumberToken(4));
        expectedTokens.add(new MultiplicationToken());
        expectedTokens.add(new NumberToken(8));

        assertEquals(expectedTokens, tokenizer.tokenize("3 + 4 * 8"));
    }

    @Test
    public void tokenizesMultipleConsecutiveNumbersThenConsecutiveOperators() throws InvalidExpressionException {
        expectedTokens.add(new NumberToken(45));
        expectedTokens.add(new NumberToken(98));
        expectedTokens.add(new NumberToken(100));
        expectedTokens.add(new DivisionToken());
        expectedTokens.add(new MultiplicationToken());
        expectedTokens.add(new SubstractToken());

        assertEquals(expectedTokens, tokenizer.tokenize("45 98 100 / *-"));
    }

    @Test
    public void raiseExceptionWhenStringIsAWord() {
        assertThrows(InvalidExpressionException.class, () -> tokenizer.tokenize("Hola"));
    }

    @Test
    public void raiseExceptionOnLetterInString() {
        assertThrows(InvalidExpressionException.class, () -> tokenizer.tokenize("15 + a"));
    }

    @Test
    public void raiseExceptionWhenBracketInString() {
        assertThrows(InvalidExpressionException.class, () -> tokenizer.tokenize("(23 + 3)"));
    }

}