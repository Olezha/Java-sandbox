package edu.olezha.sandbox.multithreading;

public class Cpu {

    private static int reasonableProcesses;

    public static int getReasonableProcesses() {
        if (reasonableProcesses > 0) {
            return reasonableProcesses;
        }

        synchronized (Cpu.class) {
            if (reasonableProcesses > 0) {
                return reasonableProcesses;
            }

            reasonableProcesses = reasonableProcesses(Runtime.getRuntime().availableProcessors());
            return reasonableProcesses;
        }
    }

    private static int reasonableProcesses(int availableProcessors) {
        if (availableProcessors < 4) {
            return 1;
        }

        if (availableProcessors < 16) {
            return availableProcessors - 2;
        }

        return availableProcessors - 4;
    }

    public static void main(String[] args) {
        System.out.println(Cpu.getReasonableProcesses());
    }
}
