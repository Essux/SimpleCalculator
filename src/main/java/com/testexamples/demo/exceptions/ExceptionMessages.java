package com.testexamples.demo.exceptions;

public class ExceptionMessages {
    private ExceptionMessages () {
        throw new IllegalStateException("Trying to instanciate a Utils class");
    }
    
    public static final String EXPRESSION_EMPTY_MESSAGE = "La expresion esta vacia";

    public static final String EXPECTED_NUMBER = "Se esperaba un numero";

    public static final String EXPECTED_OPERATOR = "Se esperaba un operador";

    public static final String UNEXPECTED_CHARACTER = "Se encontro un caracter invalido";

    public static final String DIVISION_BY_ZERO = "Division por 0";

    public static final String UNEXPECTED_TOKEN = "Se encontro un token inesperado";
}