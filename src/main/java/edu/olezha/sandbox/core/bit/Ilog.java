package edu.olezha.sandbox.core.bit;

public class Ilog {

    public static void main(String[] args) {
        for (int i = -10; i < 20; i++)
            System.out.println("i" + i + " " + ilog(i) + " " + ilog2(i) + " " + Integer.toBinaryString(i) + " " + Integer.bitCount(i));
    }

    // The minimum number of bits required to store a positive integer `a` in
    // two’s complement notation, or 0 for a non-positive integer a.
    private static int ilog(int a) {
        if (a <= 0) {
            return 0;
        }

        int n = 0;
        while (a > 0) {
            a >>= 1;
            n++;
        }

        return n;
    }

    private static int ilog2(int a) {
        if (a <= 0) {
            return 0;
        }

        return 32 - Integer.numberOfLeadingZeros(a);
    }
}
