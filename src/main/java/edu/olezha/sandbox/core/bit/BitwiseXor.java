package edu.olezha.sandbox.core.bit;

public class BitwiseXor {

    public static void main(String[] args) {
        for (int a = 7; a < 15; a++) {
            System.out.println("---");
            int b = 5, c = a ^ b;
            System.out.println(a + " ^ " + b + " = " + c);
            System.out.println(
                    Integer.toBinaryString(a)
                            + " ^ "
                            + Integer.toBinaryString(b)
                            + " = "
                            + Integer.toBinaryString(c)
            );
        }
    }
}
