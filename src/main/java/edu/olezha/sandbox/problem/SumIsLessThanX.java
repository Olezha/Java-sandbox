package edu.olezha.sandbox.problem;

/**
 * Given an array in sorted order
 * count the pairs of numbers whose sum is less than x
 */
public class SumIsLessThanX {

    public static void main(String[] args) {
        System.out.println(pairsSum(new int[] {1, 3, 5, 12, 14, 15, 16, 55, 56}, 55)); // 21
        System.out.println(pairsSum(new int[] {2, 4, 6, 8, 9}, 14)); // 7
    }

    // O(N^2 / 2)
    static int pairsSum(int[] a, int maxSum) {
        int sum = 0;
        int right = a.length;
        for (int i = 0; i < right; i++)
            for (int j = i + 1; j < right; j++)
                if (a[i] + a[j] < maxSum)
                    sum++;
                else
                    right = j;
        return sum;
    }
}
