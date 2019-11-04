package edu.olezha.sandbox.multithreading.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class ParallelRecursion {

    public <T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results) {
        for (Node<T> n : nodes) {
            results.add(n.compute());
            sequentialRecursive(n.getChildren(), results);
        }
    }

    private <T> void parallelRecursive(final Executor executor, List<Node<T>> nodes, final Collection<T> results) {
        for (final Node<T> n : nodes) {
            executor.execute(() -> {
                results.add(n.compute());
            });
            parallelRecursive(executor, n.getChildren(), results);
        }
    }

    public <T> Collection<T> getParallelResults(List<Node<T>> nodes) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedQueue<>();
        parallelRecursive(executorService, nodes, resultQueue);

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        return resultQueue;
    }
}

class Node<T> {

    private T value;
    private List<Node<T>> children;

    Node(T value, List<Node<T>> children) {
        this.value = value;
        this.children = children;
    }

    List<Node<T>> getChildren() {
        return children;
    }

    T compute() {
        synchronized (Thread.currentThread()) {
            try {
                wait(5_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return value;
    }
}
