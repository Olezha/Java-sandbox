package edu.olezha.jsandbox.multithreading.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionQueue {

    public static void main(String[] args) throws InterruptedException {
        Second second = new Second();
        new Thread(() -> {
            try {
                second.state1DependentMethod("1");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        new Thread(() -> {
            try {
                second.state2DependentMethod("2");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        new Thread(() -> {
            try {
                second.state2DependentMethod("3");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        new Thread(() -> {
            try {
                second.state1DependentMethod("4");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        First first = new First();
        new Thread(() -> {
            synchronized (Thread.currentThread()) {
                try {
                    Thread.currentThread().wait(3_000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            first.condition = true;
            synchronized (first.lock) {
                first.lock.notifyAll();
            }
        }).start();
        first.stateDependentMethod();
    }


}

class Second {

    private final Lock lock = new ReentrantLock();

    private final Condition condition1 = lock.newCondition();

    private final Condition condition2 = lock.newCondition();

    private volatile boolean condition = false;

    void state1DependentMethod(String arg) throws InterruptedException {
        lock.lock();
        try {
            while (!condition)
                condition1.await();
            System.out.println(arg + " condition = true");
            condition = false;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    void state2DependentMethod(String arg) throws InterruptedException {
        lock.lock();
        try {
            while (condition)
                condition2.await();
            System.out.println(arg + " condition = false");
            condition = true;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}

class First {

    final Object lock = new Object();

    volatile boolean condition = false;

    void stateDependentMethod() throws InterruptedException {
        synchronized (lock) { // condition predicate must be guarded by lock
            while (!conditionPredicate())
                lock.wait();

            // object is now in desired state
            System.out.println("condition = true");
        }
    }

    private boolean conditionPredicate() {
        return condition;
    }
}
