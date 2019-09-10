package edu.olezha.jsandbox.multithreading.concurrent;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Oleh on 13.11.16.
 */
public class ExecutorPullApp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            List<Future<String>> futures = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                futures.add(executorService.submit(
                        new ImplementsCallableSingleProcess("Name" + i, (long) Math.abs(RNG.random() / 1e6))));
            }

            executorService.shutdown();

            int i = 0;
            for (Future<String> future : futures) {
                try {
                    System.out.println(i++ + " " + future.get());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    System.out.println(e.getMessage());
                }
            }
        } finally {
            if (!executorService.isShutdown())
                executorService.shutdownNow();
        }
    }
}
