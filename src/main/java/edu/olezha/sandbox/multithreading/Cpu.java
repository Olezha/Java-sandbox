package edu.olezha.sandbox.multithreading;

public class Cpu {

    private static int availableProcessors;

    public int getAvailableProcessors() {
        if (availableProcessors > 0) {
            return availableProcessors;
        }

        synchronized (this) {
            if (availableProcessors > 0) {
                return availableProcessors;
            }

            availableProcessors = Runtime.getRuntime().availableProcessors();
            return availableProcessors;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Cpu().getAvailableProcessors());
    }
}
