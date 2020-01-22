package edu.olezha.sandbox.algo;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        Comparable[] a = {5, 3, 8, 12, 345, 4, 456, 2, 9, 8, 7, 34};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(Comparable[] a) {
        int h = 1;
        while (h < a.length / 3) h = h * 3 + 1;

        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j -= h) {
                    Comparable tmp = a[j];
                    a[j] = a[j - h];
                    a[j - h] = tmp;
                }
            }

            h /= 3;
        }
    }
}
