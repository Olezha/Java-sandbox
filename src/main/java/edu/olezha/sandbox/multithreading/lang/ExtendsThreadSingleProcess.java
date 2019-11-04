package edu.olezha.sandbox.multithreading.lang;

/**
 * Created by oleh on 13.11.16.
 */
public class ExtendsThreadSingleProcess extends Thread {

    private int processNumber;

    public ExtendsThreadSingleProcess(int processNumber) {
        this.processNumber = processNumber;
    }

    public void run() {
        busy((int) 2e4);
        System.out.println("    " + System.currentTimeMillis() + " finish thread " + processNumber);
    }

    private void busy(int milliseconds) {
        long sleepTime = (long) (milliseconds * 1e5);
        long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < sleepTime);
    }

}
