package edu.olezha.sandbox.problem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Finding n-th smallest number in array with O(n)
 */
public class NthSmallestNumberInArray {

    public static void main(String[] args) {
        int[] arr = {13, 7, 6, 45, 21, 9, 2, 100};

        System.out.printf("n-th element: %d" + System.lineSeparator(), nthMin(arr, 4)); // 9
        System.out.printf("n-th element: %d" + System.lineSeparator(), nthMin(arr, 1)); // 2

        System.out.printf("n-th element: %d" + System.lineSeparator(), bfprt(arr, 0)); // 2
        System.out.printf("n-th element: %d" + System.lineSeparator(), bfprt(arr, 1)); // 6
        System.out.printf("n-th element: %d" + System.lineSeparator(), bfprt(arr, 2)); // 7
        System.out.printf("n-th element: %d" + System.lineSeparator(), bfprt(arr, 3)); // 9
        System.out.printf("n-th element: %d" + System.lineSeparator(), bfprt(arr, 4)); // 13
        System.out.printf("n-th element: %d" + System.lineSeparator(), bfprt(arr, 5)); // 21
        System.out.printf("n-th element: %d" + System.lineSeparator(), bfprt(arr, 6)); // 45
        System.out.printf("n-th element: %d" + System.lineSeparator(), bfprt(arr, 7)); // 100
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

    /**
     * @param n starts from 0
     */
    private static int bfprt(int[] arr, int n) {
        if (arr.length == 0 || arr.length <= n) throw new IllegalArgumentException();
        if (arr.length == 1) return arr[0];

        int mediansSize = arr.length / 5 + (arr.length % 5 == 0 ? 0 : 1);
        int[] medians = new int[mediansSize];

        int[] a5 = new int[5];
        int size = 0;
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            a5[size++] = arr[i];

            if (i == arr.length - 1 || size == 5) {
                if (size != 5) a5 = Arrays.copyOf(a5, size);

                Arrays.sort(a5);
                medians[k++] = a5[(size - 1) / 2];
                size = 0;
            }
        }

        int median = bfprt(medians, mediansSize / 2);

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> mid = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for (int v : arr) {
            if (v < median) left.add(v);
            else if (v == median) mid.add(v);
            else right.add(v);
        }

        if (n < left.size())
            return bfprt(left.stream().mapToInt(i -> i).toArray(), n);
        else if (n < left.size() + mid.size())
            return bfprt(mid.stream().mapToInt(i -> i).toArray(), n - left.size());
        return bfprt(right.stream().mapToInt(i -> i).toArray(), n - left.size() - mid.size());
    }
}
