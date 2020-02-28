package edu.olezha.sandbox.problem;

import java.util.Arrays;

/**
 * Finding n-th smallest number in array with O(n)
 */
public class NthSmallestNumberInArray {

    public static void main(String[] args) {
        int n = 4;
        int[] arr = {13, 7, 6, 45, 21, 9, 2, 100};

        int[] nArr = new int[n];
        for (int i = 0; i < n; i++) nArr[i] = arr[0];
        for (int v : arr) {
            if (v < nArr[n - 1]) {
                nArr[n - 1] = v;
                for (int i = n - 1; i > 0; i--) {
                    if (nArr[i] < nArr[i - 1]) {
                        int x = nArr[i];
                        nArr[i] = nArr[i - 1];
                        nArr[i - 1] = x;
                    }
                }
            }
        }

        System.out.printf("n-th element: %d", nArr[n - 1]);
    }
}
