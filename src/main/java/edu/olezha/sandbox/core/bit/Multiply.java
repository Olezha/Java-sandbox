package edu.olezha.sandbox.core.bit;

import java.math.BigInteger;

public class Multiply {

    public static void main(String[] args) {
        int x = 7915541, y = 53175250;
        System.out.println(x + " * " + y + "  = " + multiply(x, y));
        System.out.println("BigInteger.multiply = "
                + BigInteger.valueOf(x).multiply(BigInteger.valueOf(y)));
    }

    public static long multiply(int a, int b) {
        long result = 0, x = a, y = b;

        while (y != 0) {
            if ((y & 1) == 1) {
                result += x;
            }
            x <<= 1;
            y >>>= 1;
        }

        return result;
    }
}
