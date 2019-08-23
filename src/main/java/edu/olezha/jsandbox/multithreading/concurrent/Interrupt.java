package edu.olezha.jsandbox.multithreading.concurrent;

public class Interrupt {

    private final static Object object = new Object();

    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        try {
            synchronized (object) {
                System.out.println(Thread.currentThread().isInterrupted());
                object.wait();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().isInterrupted());
        }

        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
        System.out.println(Thread.currentThread().isInterrupted());
    }
}
