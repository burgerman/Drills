package com.wil.practice.interview;

public class NumericValidator {

    private static int getNumeric(String input) {
        if(!isNumeric(input)) {
                throw new IllegalArgumentException("Illegal value of input.");
        }
        return Integer.valueOf(input.trim());
    }

    private static boolean isNumeric(String input) {
        String val = input.trim();
        char n;
        for(int i=0; i<val.length(); i++) {
            n = input.charAt(i);
            if(!Character.isDigit(n)) {
                return false;
            }
        }
        return true;
    }
}
