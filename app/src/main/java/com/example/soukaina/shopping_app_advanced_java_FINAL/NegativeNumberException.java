package com.example.soukaina.shopping_app_advanced_java_FINAL;

/**
 *
 * @author Soukaina
 */

// exception class for error handling (negative numbers)
public class NegativeNumberException extends Exception {

    public NegativeNumberException() {
        super("Negative Number Exception!");
    }

    public NegativeNumberException(String message) {
        super(message);
    }
}
