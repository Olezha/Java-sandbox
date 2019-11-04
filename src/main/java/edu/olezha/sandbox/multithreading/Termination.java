package edu.olezha.sandbox.multithreading;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Termination from the Runnable (for very specific Executor)
 */
public class Termination {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Map<CountDownLatch, Thread> activeThreads = new ConcurrentHashMap<>();
        long start = System.currentTimeMillis();
        Thread mainThread = Thread.currentThread();

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            CountDownLatch latch = new CountDownLatch(1);
            activeThreads.put(latch, Thread.currentThread());
            executor.submit(() -> {
                Thread currentThread = Thread.currentThread();
                currentThread.setName("thread" + finalI);
                System.out.println(currentThread.getName() + " started");
                activeThreads.put(latch, currentThread);
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        if (start + TimeUnit.SECONDS.toMillis(5) < System.currentTimeMillis()) {
                            System.out.println(currentThread.getName() + " is alive");
                        }

                        TimeUnit.SECONDS.sleep(20);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                    activeThreads.remove(latch);
                    System.out.println(Thread.currentThread().getName() + " finished");
                }
            });
        }

        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("    start stopping");
            long startWaiting = System.currentTimeMillis();
            while (!activeThreads.isEmpty()) {
                for (Map.Entry<CountDownLatch, Thread> threadCountDownLatchEntry : activeThreads.entrySet()) {
                    Thread thread = threadCountDownLatchEntry.getValue();
                    if (!mainThread.equals(thread)) {
                        thread.interrupt();
                        System.out.println(thread.getName() + " interrupted");
                        threadCountDownLatchEntry.getKey().await();
                        System.out.println(" finish waiting " + thread.getName());
                    }
                }
                System.out.println("    activeThreads.size:" + activeThreads.size());
            }
            System.out.println("    waiting: " + (System.currentTimeMillis() - startWaiting));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
