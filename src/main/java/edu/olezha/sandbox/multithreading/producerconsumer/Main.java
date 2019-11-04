package edu.olezha.sandbox.multithreading.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    private static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        executorService.submit(new Producer(queue, flag));
        executorService.submit(new Consumer(queue, flag, Main::found));

        while (flag.get())
            if (queue.size() > 400)
                executorService.submit(new Consumer(queue, flag, Main::found));

        executorService.shutdown();
    }

    private static void found(String answer) {
        flag.set(false);
        System.out.println("answer: " + answer);
    }
}
