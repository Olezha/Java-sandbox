package edu.olezha.sandbox.problem;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Suppose there are millions of numbers and you have to print max 20 elements
 * How will you do that?
 */
public class Max20elements {

    public static void main(String[] args) {
//        int[] in = new int[1_000_000]; // 4 b * 10^6 ~= 3,8 mb
        Queue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
        Random random = new Random();
        for (int i = 0; i < 1_000; i++) {
            max20elements(random.nextInt(1_000), pq);
        }
        System.out.println(pq.toString());
    }

    // O(NlogM)
    static void max20elements(int in, Queue<Integer> pq) {
        pq.add(in);
        if (pq.size() > 20) pq.poll();
    }
}
