package edu.olezha.sandbox.multithreading;

public class ThreadWait {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> System.out.println("te"));

        synchronized (thread) {
            thread.start();
            thread.wait();
        }

        System.out.println("me");
    }
}
