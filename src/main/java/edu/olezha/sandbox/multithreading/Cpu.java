package edu.olezha.sandbox.multithreading;

public class Cpu {

    private static int reasonableProcesses;

    public int getAvailableProcessors() {
        if (reasonableProcesses > 0) {
            return reasonableProcesses;
        }

        synchronized (this) {
            if (reasonableProcesses > 0) {
                return reasonableProcesses;
            }

            reasonableProcesses = reasonableProcesses(Runtime.getRuntime().availableProcessors());
            return reasonableProcesses;
        }
    }

    private int reasonableProcesses(int availableProcessors) {
        if (availableProcessors < 4) {
            return 1;
        }

        if (availableProcessors < 16) {
            return availableProcessors - 2;
        }

        return availableProcessors - 4;
    }

    public static void main(String[] args) {
        System.out.println(new Cpu().getAvailableProcessors());
    }
}
