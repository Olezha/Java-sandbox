package edu.olezha.sandbox.junit;

/**
 * Created by oleh on 12.11.16.
 */
public class Math {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double divide(double a, double b) {
        if (b == 0) throw new DivByZeroException();
        return a / b;
    }

    public static double sin(double a) {
        return 0;
    }

}
