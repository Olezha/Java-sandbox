package edu.olezha.sandbox.problem;

import java.util.Arrays;

/**
 * Given the array of IDs, which contains
 * many duplicate integers and one unique integer,
 * find the unique integer.
 */
public class UniqueIntegerInArray {

    public static void main(String[] args) {
        System.out.println(unique(new int[] {1, 2, 3, 4, 5, 6, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9})); // 7
    }

    // O(NlgN)
    static int unique(int[] a) {
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        for (int i = 0; i < a.length - 1; i += 2) {
            if (a[i] != a[i + 1]) {
                return a[i];
            }
        }
        throw new IllegalStateException();
    }
}
