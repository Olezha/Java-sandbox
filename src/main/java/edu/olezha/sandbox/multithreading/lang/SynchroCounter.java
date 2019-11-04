package edu.olezha.sandbox.multithreading.lang;

/**
 * Created by oleh on 13.11.16.
 */
public class SynchroCounter {

    private long counter;

    public void incrementCounter() {
        counter++;
    }

    public long getCounter() {
        return counter;
    }

}
