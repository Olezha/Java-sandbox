package edu.olezha.sandbox.problem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given the array of IDs, which contains
 * many duplicate integers and one unique integer,
 * find the unique integer.
 */
public class UniqueIntegerInArray {

    public static void main(String[] args) {
        System.out.println(unique2(new int[] {1, 2, 3, 4, 5, 6, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9})); // 7
        System.out.println(unique2(new int[] {1, 2, 3, 4, 1, 2, 4})); // 3
        System.out.println(unique2(new int[] {100, 33, 27, 89, 27, 99, 33, 99, 100})); // 89
    }

    // O(NlgN)
    static int unique(int[] a) {
        Arrays.sort(a);
        for (int i = 0; i < a.length - 1; i += 2) {
            if (a[i] != a[i + 1]) {
                return a[i];
            }
        }
        throw new IllegalStateException();
    }

    // O(N)?
    static int unique2(int[] a) {
        Set<Integer> known = new HashSet<>();
        for (int e : a) {
            if (known.contains(e)) known.remove(e);
            else known.add(e);
        }
        if (known.size() != 1) throw new IllegalStateException();
        return (int) known.toArray()[0];
    }
}
