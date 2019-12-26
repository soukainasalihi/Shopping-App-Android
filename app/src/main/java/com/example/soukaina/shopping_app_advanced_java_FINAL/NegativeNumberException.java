package com.example.soukaina.shopping_app_advanced_java_FINAL;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
