package edu.olezha.sandbox.core.bit;

public class ShiftRightZeroFill {

    public static void main(String[] args) {
        xeroFillRightShift(1024, 10);
        System.out.println("---");
        xeroFillRightShift(-1024, 10);
        System.out.println("---");
        xeroFillRightShift(63, 7);
        System.out.println("---");
        xeroFillRightShift(-63, 7);
    }

    private static void xeroFillRightShift(int a, int j) {
        for (int i = 0; i < j; i++) {
            System.out.println("---");
            int c = a >>> 1;
            System.out.println(a + " >>> " + 1 + " = " + c + " = " + a + " >= 0 ? " + a + " / 2");
            System.out.println(
                    Integer.toBinaryString(a)
                            + " >>> "
                            + Integer.toBinaryString(1)
                            + " = "
                            + Integer.toBinaryString(c)
            );
            a = c;
        }
    }
}
