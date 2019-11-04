package edu.olezha.sandbox.multithreading.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

class Producer implements Runnable {

    private final BlockingQueue<String> queue;

    private final AtomicBoolean flag;

    Producer(final BlockingQueue<String> queue, final AtomicBoolean flag) {
        this.queue = queue;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag.get()) {
            System.out.println("queue.size: " + queue.size());
            IntStream.range(0, 100).forEach(i -> {
                if (flag.get()) {
                    queue.add("abc" + i);
                }
            });
        }
    }
}
