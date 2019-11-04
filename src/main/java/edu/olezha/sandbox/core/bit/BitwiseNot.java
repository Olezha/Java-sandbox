package edu.olezha.sandbox.core.bit;

public class BitwiseNot {

    public static void main(String[] args) {
        byte a = 47;
        byte b = (byte) ~a;
        System.out.println("~" + a + " = " + b
                + " (~" + Integer.toBinaryString(a) + " = " + Integer.toBinaryString(b) + ")");

        short c = 350;
        short d = (short) ~c;
        System.out.println("~" + c + " = " + d);

        int e = 555555555;
        int f = ~e;
        System.out.println("~" + e + " = " + f
                + " (~" + Integer.toBinaryString(e) + " = " + Integer.toBinaryString(f) + ")");

        long g = 111112222233333L;
        long h = ~g;
        System.out.println("~" + g + " = " + h);
    }
}
