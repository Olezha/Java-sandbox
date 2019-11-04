package edu.olezha.sandbox.core;

public class Boxing {

    public static void main(String[] args) {
        Byte x = 127;
        System.out.println("x = " + x);

        byte y = x++;
        System.out.println("x = " + x + "; y = " + y + "; ++y = " + ++y);
    }

}
