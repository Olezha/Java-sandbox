package edu.olezha.sandbox.multithreading.concurrent;

public class UncaughtExceptionHandler {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Something happened");
        });
        thread.setUncaughtExceptionHandler((t, e) -> System.out.println("UncaughtExceptionHandler: " + e.getMessage()));
        thread.start();
    }
}
