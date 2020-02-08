package edu.olezha.sandbox.problem;

import java.util.Arrays;
import java.util.Random;

/**
 * Suppose there are millions of numbers and you have to print max 20 elements
 * How will you do that?
 */
public class Max20elements {

    public static void main(String[] args) {
//        int[] in = new int[1_000_000]; // 4 b * 10^6 ~= 3,8 mb
        int[] out = new int[20];
        Random random = new Random();
        for (int i = 0; i < 1_000_000_000; i++) {
            max20elements(random.nextInt(1_000_000), out);
        }
        System.out.println(Arrays.toString(out));
    }

    // O(n^2)
    static void max20elements(int in, int[] out) {
        for (int i = 0; i < out.length; i++) {
            if (out[i] < in) {
                out[i] = in;
                return;
            }
        }
    }
}
