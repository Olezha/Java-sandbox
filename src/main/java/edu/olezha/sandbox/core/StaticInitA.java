package edu.olezha.sandbox.core;

public class StaticInitA {

    public static final int A = 1;

    static {
        System.out.println("A: " + A);
    }
}
