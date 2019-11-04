package edu.olezha.sandbox.multithreading.practice;

public class ReadModifyWrite {

    private static int i;
    private static int j;
    private static final Object lock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 500_000; i++) {
                    ReadModifyWrite.i++;
                    incrementJ();
                }
            }
        }).start();

        for (int i = 0; i < 500_000; i++) {
            ReadModifyWrite.i++;
            incrementJ();
        }

        synchronized (lock) {
            System.out.println("i: " + i + System.lineSeparator() + "j: " + j);
        }
    }

    private static synchronized void incrementJ() {
        j++;
    }
}
