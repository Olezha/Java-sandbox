package edu.olezha.jsandbox.core.bit;

import java.math.BigInteger;

public class ShiftRight {

    private static short bits = 211;

    public static void main(String[] args) {
        shiftRight(1024, 10);
        System.out.println("---");
        shiftRight(63, 7);
        System.out.println("---");
        shiftRight(-63, 7);

        BigInteger bi = BigInteger.valueOf(bits);
        System.out.println("0x" + bi.toString(16) + " = 0b" + bi.toString(2));
        for (int i = 0; i < 8; i++)
            System.out.println(getBit(i));
    }

    private static void shiftRight(int a, int j) {
        for (int i = 0; i < j; i++) {
            System.out.println("---");
            int c = a >> 1;
            System.out.println(a + " >> " + 1 + " = " + c + " = " + a + " / 2");
            System.out.println(
                    Integer.toBinaryString(a)
                            + " >> "
                            + Integer.toBinaryString(1)
                            + " = "
                            + Integer.toBinaryString(c)
            );
            a = c;
        }
    }

    private static int getBit(int i) {
        return (bits >> i) & 1;
    }
}
