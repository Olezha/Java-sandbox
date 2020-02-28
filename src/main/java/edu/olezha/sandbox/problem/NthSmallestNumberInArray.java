package edu.olezha.sandbox.problem;

import java.util.Arrays;

/**
 * Finding n-th smallest number in array with O(n)
 */
public class NthSmallestNumberInArray {

    public static void main(String[] args) {
        int n = 4;
        int[] arr = {13, 7, 6, 45, 21, 9, 2, 100};
        Arrays.sort(arr);

        System.out.printf("n-th element: %d", arr[n-1]);
    }
}
