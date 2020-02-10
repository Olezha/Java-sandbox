package edu.olezha.sandbox.problem;

import java.util.Arrays;

public class HighestProductOfTriplet {

    public static void main(String[] args) {
        System.out.println("1200 " + highestProductOfTriplet(new int[] {10, 3, 5, 6, 20}));
        System.out.println("-90 " + highestProductOfTriplet(new int[] {-10, -3, -5, -6, -20}));
        System.out.println("168 " + highestProductOfTriplet(new int[] {1, -4, 3, -6, 7, 0}));
    }

    // O(NLgN)
    static int highestProductOfTriplet(int[] a) {
        Arrays.sort(a);
        return Math.max(
                a[a.length - 3] * a[a.length - 2] * a[a.length - 1],
                a[0] * a[1] * a[a.length - 1]
        );
    }
}
