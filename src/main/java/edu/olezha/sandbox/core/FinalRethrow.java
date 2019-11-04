package edu.olezha.sandbox.core;

public class FinalRethrow {

    public static void main(String[] args) {
        try {
            throw new ArrayIndexOutOfBoundsException();
        } catch (Exception e) {
            System.out.println("catch Exception");
            throw e;
        }
    }
}
