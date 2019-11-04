package edu.olezha.sandbox.multithreading.concurrent;

public class Shutdown {

    private static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("stopping...");
            try {
                synchronized (object) {
                    object.wait(3_000);
                }
            } catch (InterruptedException ignored) {
            }
            System.out.println("has stopped");
        }));

        synchronized (object) {
            object.wait(30_000);
            System.out.println("finish");
        }
    }
}
