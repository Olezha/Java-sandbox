package edu.olezha.jsandbox.multithreading.concurrent;

import java.util.concurrent.CountDownLatch;

public class TestHarness {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new TestHarness().timeTasks(5, () -> {}));
        System.out.println(new TestHarness().timeTasks(6, () -> {}));
    }

    private long timeTasks(int threads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException ignored) {
                }
            });
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();

        return end - start;
    }
}
