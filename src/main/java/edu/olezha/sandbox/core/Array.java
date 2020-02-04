package edu.olezha.sandbox.core;

import java.util.Arrays;

public class Array {

    public static void main(String[] args) {
        int[] x = new int[3];
        System.out.println(x.getClass().getName());

        byte[][] y = new byte[3][];
        System.out.println(y.getClass().getName());

        System.out.println(args.getClass().getName());

        Person[] p = new Person[3];
        System.out.println(p.getClass().getName());

        int[] a = {1, 2, 3, 4, 5, 6};
        for (int i = 0; i < a.length; i++) {
            if (i % 2 == 0) a[i]++;
            else a[i]--;
        }
        System.out.println(Arrays.toString(a));
    }
}
