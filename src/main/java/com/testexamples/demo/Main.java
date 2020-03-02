package com.testexamples.demo;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Logger logger = Logger.getLogger("user-logger");

        while (true) {
            logger.log(Level.INFO, UserInteractionMessages.INTRODUCTORY_MESSAGE);
            
            try {
                String input = scanner.nextLine();
                if (UserInteractionMessages.EXIT_COMMAND.equals(input)) break;
                
                Tokenizer tokenizer = new Tokenizer();
                TokenEvaluator evaluator = new TokenEvaluator();
                
                int result = evaluator.evaluateTokens(tokenizer.tokenize(input));
                logger.log(Level.INFO, "{0}", result);
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }
}