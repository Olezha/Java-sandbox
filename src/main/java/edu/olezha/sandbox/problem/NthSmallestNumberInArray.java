package edu.olezha.sandbox.problem;

import java.util.Arrays;

/**
 * Finding n-th smallest number in array with O(n)
 */
public class NthSmallestNumberInArray {

    public static void main(String[] args) {
        int[] arr = {13, 7, 6, 45, 21, 9, 2, 100};

        System.out.printf("n-th element: %d" + System.lineSeparator(), nthMin(arr, 4)); // 9
        System.out.printf("n-th element: %d" + System.lineSeparator(), nthMin(arr, 1)); // 2
    }

    private static int nthMin(int[] arr, int n) {
        int[] nArr = Arrays.copyOf(arr, n);
        Arrays.sort(nArr);

        for (int i = n; i < arr.length; i++) {
            if (arr[i] < nArr[n - 1]) {
                int j = n - 1;
                for (; j > 0; j--) {
                    if (nArr[j - 1] > arr[i]) {
                        nArr[j] = nArr[j - 1];
                    } else {
                        break;
                    }
                }
                nArr[j] = arr[i];
            }
        }

        return nArr[n - 1];
    }
}
