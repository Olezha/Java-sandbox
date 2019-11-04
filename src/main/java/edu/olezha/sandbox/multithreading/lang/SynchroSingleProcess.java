package edu.olezha.sandbox.multithreading.lang;

/**
 * Created by oleh on 13.11.16.
 */
public class SynchroSingleProcess implements Runnable {

    private SynchroCounter counter;
    SynchroSynchronizedCounter synchronizedCounter;

    public SynchroSingleProcess(SynchroCounter counter, SynchroSynchronizedCounter synchronizedCounter) {
        this.counter = counter;
        this.synchronizedCounter = synchronizedCounter;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.incrementCounter(); // !synchronized
        }

        for (int i = 0; i < 1000; i++) {
            synchronizedCounter.incrementCounter();
        }
    }

}
