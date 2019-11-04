package edu.olezha.sandbox.core.bit;

public class TurnOffRightmostBit {

    public static void main(String[] args) {
        long x = 4412;
        System.out.println(Long.toBinaryString(x));
        while (x != 0) {
            x = turnOffRightmostBit(x);
            System.out.println(Long.toBinaryString(x));
        }
    }

    public static long turnOffRightmostBit(long x) {
        return x & (x-1);
    }
}
