package edu.olezha.sandbox.math;

public class Abs {

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(abs(Integer.MIN_VALUE));
    }

    public static int abs(int i) {
        return i < 0 ? -i : i;
    }
}
