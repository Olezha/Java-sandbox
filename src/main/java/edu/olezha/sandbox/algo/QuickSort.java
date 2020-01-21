package edu.olezha.sandbox.algo;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        Comparable[] a = {0, 7, 6, 5, 1, 2, 3};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(Comparable[] a) {
        // shuffle needed
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        for (; ; ) {
            while (a[++i].compareTo(v) < 0) if (i == hi) break;
            while (v.compareTo(a[--j]) < 0) if (j == lo) break;
            if (i >= j) break;

            Comparable tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        Comparable tmp = a[lo];
        a[lo] = a[j];
        a[j] = tmp;

        return j;
    }
}
