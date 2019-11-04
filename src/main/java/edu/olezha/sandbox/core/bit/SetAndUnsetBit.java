package edu.olezha.sandbox.core.bit;

public class SetAndUnsetBit {

    public static void main(String[] args) {
        long x = 123;
        System.out.printf("%25s%n", Long.toBinaryString(x));
        x = setBit(x, 2);
        System.out.printf("%15s%10s%n","setBit 2: ", Long.toBinaryString(x));
        x = unsetBit(x, 0);
        System.out.printf("%15s%10s%n","unsetBit 0: ", Long.toBinaryString(x));x = setBit(x, 2);
        x = unsetBit(x, 1);
        System.out.printf("%15s%10s%n","unsetBit 1: ", Long.toBinaryString(x));x = setBit(x, 2);
        x = unsetBit(x, 2);
        System.out.printf("%15s%10s%n","unsetBit 2: ", Long.toBinaryString(x));
        x = setBit(x, 1);
        System.out.printf("%15s%10s%n","setBit 1: ", Long.toBinaryString(x));
        x = toggleBit(x, 2);
        System.out.printf("%15s%10s%n","toggleBit 2: ", Long.toBinaryString(x));
        x = toggleBit(x, 2);
        System.out.printf("%15s%10s%n","toggleBit 2: ", Long.toBinaryString(x));
    }

    public static long setBit(long x, int n) {
        return x | (1<<n);
    }

    public static long unsetBit(long x, int n) {
        return x & ~(1<<n);
    }

    public static long toggleBit(long x, int n) {
        return x ^ (1<<n);
    }
}
