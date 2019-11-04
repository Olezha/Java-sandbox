package edu.olezha.sandbox.multithreading.lang;

import java.util.List;

/**
 * Created by oleh on 13.11.16.
 */
public class ImplementsRunnableSingleProcess implements Runnable {

    private double randomResult;
    private List<Double> results;

//    private String name;

    public ImplementsRunnableSingleProcess() {}

    public ImplementsRunnableSingleProcess(List<Double> results) {
        this.results = results;
    }

    public void run() {
//        System.out.println(name.length()); // npe for ExecutorApp

        randomResult = busy((int) 2e4);

        if (results != null)
            results.add(randomResult);

        System.out.println(System.currentTimeMillis() + " runnable finished");
    }

    private double busy(int milliseconds) {
        long sleepTime = (long) (milliseconds*1e5);
        long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < sleepTime);
        return Math.random();
    }

    public double getRandomResult() {
        return randomResult;
    }

}
