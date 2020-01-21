package edu.olezha.sandbox.algo;

import java.util.Arrays;

public class MergeSort<E> {

    private Comparable<E>[] aux;

    public static void main(String[] args) {
        Integer[] a = {3, 2, 7, 6, 7, 1, 12, 4, 15, 9};
        new MergeSort<Integer>().sort(a);
        System.out.println(Arrays.toString(a));
    }

    private void sort(Comparable<E>[] a) {
        aux = a.clone();
        sort(a, 0, a.length - 1);
    }

    /**
     * Sort a[lo..hi]
     */
    private void sort(Comparable<E>[] a, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    /**
     * Merge a[lo..mid] with a[mid+1..hi]
     */
    private void merge(Comparable<E>[] a, int lo, int mid, int hi) {
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i].compareTo((E) aux[j]) > 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}
