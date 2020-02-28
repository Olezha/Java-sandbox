package edu.olezha.sandbox.problem;

import java.util.Arrays;

/**
 * Finding n-th smallest number in array with O(n)
 */
public class NthSmallestNumberInArray {

    public static void main(String[] args) {
        int n = 4;
        int[] arr = {13, 7, 6, 45, 21, 9, 2, 100};

        int[] nArr = Arrays.copyOf(arr, n);
        Arrays.sort(nArr);
        for (int i = n; i < arr.length; i++) {
            if (arr[i] < nArr[n - 1]) {
                nArr[n - 1] = arr[i];
                for (int j = n - 1; j > 0; j--) {
                    if (nArr[j] < nArr[j - 1]) {
                        int tmp = nArr[j];
                        nArr[j] = nArr[j - 1];
                        nArr[j - 1] = tmp;
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.printf("n-th element: %d", nArr[n - 1]);
    }
}
