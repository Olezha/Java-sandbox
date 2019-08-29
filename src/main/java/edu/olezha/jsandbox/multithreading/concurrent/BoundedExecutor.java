package edu.olezha.jsandbox.multithreading.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

public class BoundedExecutor {

    private final Executor executor;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor executor, int bound) {
        this.executor = executor;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();
        try {
            executor.execute(() -> {
                try {
                    command.run();
                } finally {
                    semaphore.release();
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedExecutor boundedExecutor = new BoundedExecutor(Executors.newCachedThreadPool(), 5);
        for (int i = 0; i < 100; i++) {
            boundedExecutor.submitTask(() -> {
                synchronized (Thread.currentThread()) {
                    try {
                        Thread.currentThread().wait((int)(Math.random() * 10_000));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("done");
                }
            });
        }
    }
}
