package edu.olezha.sandbox.core.bit;

public class ShiftLeft {

    public static void main(String[] args) {
        int a = 1, b = 1;
        for (int i = 0; i < 10; i++) {
            System.out.println("---");
            int c = a << b;
            System.out.println(a + " << " + b + " = " + c + " = " + a + " * 2");
            System.out.println(
                    Integer.toBinaryString(a)
                    + " << "
                    + Integer.toBinaryString(b)
                    + " = "
                    + Integer.toBinaryString(c)
            );
            a = c;
        }
    }
}
