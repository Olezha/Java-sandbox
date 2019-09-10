package edu.olezha.jsandbox.multithreading.concurrent;

import java.util.concurrent.Callable;

/**
 * Created by Oleh on 13.11.16.
 */
public class ImplementsCallableSingleProcess implements Callable<String> {

    private String name;

    private long millis;

    public ImplementsCallableSingleProcess() {
    }

    public ImplementsCallableSingleProcess(String name, long millis) {
        this.name = name;
        this.millis = millis;
    }

    public String call() throws Exception {
        if (millis > 0)
            Thread.sleep(millis);
        else
            Thread.sleep((long) 1e3);
        System.out.println("ImplementsCallableSingleProcess " + name + " done " + millis);
        return "ImplementsCallableSingleProcessCallable<String>: " + name + " (the best option)";
    }
}
