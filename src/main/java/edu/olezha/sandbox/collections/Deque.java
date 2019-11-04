package edu.olezha.sandbox.collections;

import java.util.ArrayDeque;

/**
 * Created by oleh on 12.11.16.
 */
public class Deque {

    public static void main(String[] args) {
        java.util.Deque<String> deque = new ArrayDeque<>();

        for (int i = 0; i <= 10; i++) {
            String e = "E" + i;
            deque.push(e);
            System.out.println(deque);
        }

        System.out.println("--------------");

        while (deque.peek() != null) {
            System.out.println(deque.pollLast());
            System.out.println(deque.pollFirst());
        }
    }

}
