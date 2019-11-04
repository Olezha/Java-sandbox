package edu.olezha.sandbox.core;

public class Array {

    public static void main(String[] args) {
        int[] x = new int[3];
        System.out.println(x.getClass().getName());

        byte[][] y = new byte[3][];
        System.out.println(y.getClass().getName());

        System.out.println(args.getClass().getName());

        Person[] p = new Person[3];
        System.out.println(p.getClass().getName());
    }
}
