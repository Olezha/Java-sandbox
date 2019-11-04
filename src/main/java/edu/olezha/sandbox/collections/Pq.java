package edu.olezha.sandbox.collections;

import java.util.PriorityQueue;
import java.util.Queue;

public class Pq {

    public static void main(String[] args) {
        Queue<Integer> pq = new PriorityQueue<>(16);
//        pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        pq.offer(15);
        pq.offer(-15);
        pq.offer(12);
        pq.offer(21);
        pq.offer(32);
        pq.offer(8);
        pq.offer(5);
        pq.offer(122);
        pq.offer(64);
        while (pq.peek() != null) {
            System.out.println(pq.poll());
        }

        for (int i = 0; i < 32; i++) {
            pq.offer(i);
        }
        System.out.println("pq.size: " + pq.size());
    }
}
