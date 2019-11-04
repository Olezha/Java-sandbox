package edu.olezha.sandbox.core.bit;

import java.math.BigInteger;

public class MultiplyLong {

    public static void main(String[] args) {
        long x = 99689463345168L, y = 254236690803646L;
        System.out.println(x + " * " + y + "  = " + multiply(x, y));
        System.out.println("BigInteger.multiply               = "
                + BigInteger.valueOf(x).multiply(BigInteger.valueOf(y)));

        System.out.println();

        x = 428380349403380L;
        y = 412272392551456L;
        BigInteger res1 = multiply(x, y);
        System.out.println(x + " * " + y + "  = " + res1 + " " + res1.toString(2));
        BigInteger res2 = BigInteger.valueOf(x).multiply(BigInteger.valueOf(y));
        System.out.println("BigInteger.multiply                = "
                + res2 + " " + res2.toString(2));
    }

    /**
     * for non-negative operands
     */
    public static BigInteger multiply(long x, long y) {
        System.out.println(String.format("%64s", Long.toBinaryString(x)).replace(' ', '0') + "  *  "
                + String.format("%64s", Long.toBinaryString(y)).replace(' ', '0'));

        final long x_hi = x >>> 32;
        final long y_hi = y >>> 32;
        final long x_lo = x & 0xFFFFFFFFL;
        final long y_lo = y & 0xFFFFFFFFL;
        System.out.println(String.format("%32s", Long.toBinaryString(x_hi)).replace(' ', '0') + " "
                + String.format("%32s", Long.toBinaryString(x_lo)).replace(' ', '0')
                + " *  "
                + String.format("%32s", Long.toBinaryString(y_hi)).replace(' ', '0') + " "
                + String.format("%32s", Long.toBinaryString(y_lo)).replace(' ', '0'));

        long highHalf = x_lo * y_lo;
        highHalf >>>= 32;
        highHalf += x_hi * y_lo + x_lo * y_hi;
        highHalf >>>= 32;
        highHalf += x_hi * y_hi;

        long lowHalf = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                lowHalf += x;
            }
            x <<= 1;
            y >>>= 1;
        }

        System.out.println(highHalf + " " + lowHalf);

        return new BigInteger(Long.toBinaryString(highHalf)
                + String.format("%64s", Long.toBinaryString(lowHalf)).replace(' ', '0'),
                2);
    }
}
