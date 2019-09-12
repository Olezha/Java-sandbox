package edu.olezha.jsandbox.multithreading.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class Updater {

    public static void main(String[] args) throws InterruptedException {
        Node<String> head = new Node<>("head");

        int N = 10_000;
        CountDownLatch latch = new CountDownLatch(N);
        List<Thread> threads = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            int finalI = i;
            threads.add(new Thread(() -> {
                Node<String> next = new Node<>("next" + finalI);
                for (; ; ) {
                    Node tail = head;
                    while (nextUpdater.get(tail) != null)
                        tail = nextUpdater.get(tail);

                    if (nextUpdater.compareAndSet(tail, null, next)) {
                        latch.countDown();
                        return;
                    } else {
                        System.out.println("try again");
                    }
                }
            }));
        }

        for (Thread thread : threads)
            thread.start();

        latch.await(5, TimeUnit.SECONDS);
        System.out.println(head.count());
    }

    private static final AtomicReferenceFieldUpdater<Node, Node> nextUpdater =
            AtomicReferenceFieldUpdater.newUpdater(Node.class, Node.class, "next");

    private static class Node<E> {
        private final E item;
        volatile Node<E> next;

        Node(E item) {
            this.item = item;
        }

        int count() {
            int i = 1;
            Node next = this.next;
            while ((next = next.next) != null)
                i++;
            return i;
        }

        @Override
        public String toString() {
            return item + "(" + next + ")";
        }
    }
}
