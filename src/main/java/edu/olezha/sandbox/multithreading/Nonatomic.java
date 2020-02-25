package edu.olezha.sandbox.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Non-atomic operation on volatile field 'i'
 * Do not do that
 */
public class Nonatomic {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        ExecutorService executor = Executors.newFixedThreadPool(1_000);
        for (int i = 0; i < 1_000_000; i++)
            executor.submit(() -> {
//                synchronized (counter) {
                    counter.i++;
//                }
            });
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println(counter.i);
    }
}

class Counter {
    volatile int i;
}
