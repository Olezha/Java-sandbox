package edu.olezha.sandbox.algo;

import edu.olezha.sandbox.multithreading.concurrent.RNG;

import java.util.ArrayList;
import java.util.List;

public class MaxPQ<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        int size = 12;
        List<Integer> list = new ArrayList<>(size);
        MaxPQ<Integer> pq = new MaxPQ<>();
        for (int i = 0; i < size; i++) {
            int random = RNG.random();
            list.add(random);
            pq.add(random);
        }
        System.out.println(list);
        list = new ArrayList<>(size);
        for (; !pq.isEmpty(); ) {
            list.add(pq.top());
        }
        System.out.println(list);
    }

    /**
     * Storing sorted binary tree
     * pq[i].parent = pq[i/2]
     * pq[i].left = pq[2i]
     * pq[i].right = pq[2i+1]
     */
    private Key[] pq = (Key[]) new Comparable[11];

    /**
     * pq[1..n]
     * pq[0] will not be used
     */
    private int n = 0;

    public void add(Key v) {
        ensureCapacity();
        pq[++n] = v;
        swim(n);
    }

    public Key top() {
        Key top = pq[1];
        exchange(1, n);
        pq[n--] = null;
        sink(1);
        return top;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void ensureCapacity() {
        if (pq.length < n + 2) {
            Key[] newPq = (Key[]) new Comparable[pq.length * 2];
            System.arraycopy(pq, 1, newPq, 1, n);
            pq = newPq;
        }
    }

    private void swim(int i) {
        while (i > 1 && less(i / 2, i)) {
            exchange(i / 2, i);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(j, j + 1)) j++;
            if (!less(i, j)) break;
            exchange(i, j);
            i = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exchange(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }
}
