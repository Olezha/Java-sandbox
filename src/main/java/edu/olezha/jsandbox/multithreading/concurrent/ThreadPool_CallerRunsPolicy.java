package edu.olezha.jsandbox.multithreading.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool_CallerRunsPolicy {

    private static final int N_THREADS = 3;
    private static final int CAPACITY = 2;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(N_THREADS, N_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(CAPACITY));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for (AtomicInteger i = new AtomicInteger(); i.get() < 100; i.incrementAndGet()) {
            try {
                executor.submit(() -> {
                    try {
                        System.out.println("job: " + i.get() + "; queue: " + executor.getQueue().size() +
                                "; thread: " + Thread.currentThread().getName());
                        synchronized (Thread.currentThread()) {
                            Thread.currentThread().wait((int)(Math.random() * 10_000));
                        }
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                        Thread.currentThread().interrupt();
                    }
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
