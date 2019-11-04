package edu.olezha.sandbox.multithreading.lang;

/**
 * Created by oleh on 13.11.16.
 */
public class SynchroSynchronizedCounter {

    private long counter;

    public synchronized void incrementCounter() {
        counter++;
    }

    public long getCounter() {
        return counter;
    }

}
