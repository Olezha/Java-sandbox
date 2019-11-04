package edu.olezha.sandbox.core;

public class ByValueVSByReference {

    public static void main(String[] args) {
        int x = 1;
        System.out.println("int x = " + x);
        modify(x);
        System.out.println("After modify(x): x = " + x + "\n");

        Integer X = x;
        System.out.println("Integer X = " + X);
        modify(X);
        System.out.println("After modify(X): X = " + X + "\n");

        Int intX = new Int(x);
        System.out.println("Int intX = " + intX);
        modify(intX);
        System.out.println("After modify(intX): intX = " + intX);
    }

    private static void modify(int x) {
        x *= 10;
    }

    private static void modify(Integer x) {
        x *= 10;
    }

    private static void modify(Int x) {
        x.x *= 10;
    }

    private static class Int {

        int x;

        Int(int x) {
            this.x = x;
        }

        public String toString() {
            return String.valueOf(x);
        }

    }

}
