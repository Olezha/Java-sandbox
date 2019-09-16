package edu.olezha.jsandbox.multithreading.concurrent;

public class PossibleReordering {

    private static int x, y, a, b;

    private static void possibleReordering() throws InterruptedException {
        x = y = a = b = 0;

        Thread one = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread other = new Thread(() -> {
            b = 1;
            y = a;
        });

        one.start();
        other.start();
        one.join();
        other.join();
        System.out.print("(" + x + "," + y + ") ");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                possibleReordering();
            System.out.println();
        }
    }
}
