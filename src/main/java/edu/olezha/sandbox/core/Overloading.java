package edu.olezha.sandbox.core;

public class Overloading {

    public static void main(String[] args) {
        byte b = 1;
        act(b, b);
    }

    private static void act(byte... bs) {
        System.out.println("var args");
    }

    private static void act(short s, long l) {
        System.out.println("short with long");
    }

    private static void act(Byte b1, Byte b2) {
        System.out.println("Byte Byte");
    }
}
