package edu.olezha.jsandbox.multithreading.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            for (int i = 0; i < 7; i++) {
                try {
                    synchronized (Thread.currentThread()) {
                        Thread.currentThread().wait(1_000);
                    }
                    System.out.println(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " locked");
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " tryLock: " + lock.tryLock(2, TimeUnit.SECONDS));
                    System.out.println(Thread.currentThread().getName() + " tryLock: " + lock.tryLock(5, TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " unlock");
                }
            }).start();
            synchronized (Thread.currentThread()) {
                Thread.currentThread().wait(4_000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " unlock");
        }
    }
}
