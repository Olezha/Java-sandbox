package edu.olezha.sandbox.multithreading.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh on 13.11.16.
 */
public class ImplementsRunnableApp {

    public static void main(String[] args) throws InterruptedException {
        List<ImplementsRunnableSingleProcess> implementsRunnableSingleProcesses = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ImplementsRunnableSingleProcess implementsRunnableSingleProcess = new ImplementsRunnableSingleProcess();
            implementsRunnableSingleProcesses.add(implementsRunnableSingleProcess);
            Thread thread = new Thread(implementsRunnableSingleProcess);
            thread.start();
        }

        for (ImplementsRunnableSingleProcess implementsRunnableSingleProcess : implementsRunnableSingleProcesses) {
            System.out.println("- " + implementsRunnableSingleProcess.getRandomResult());
        }

        Thread.sleep((long) 5e3); // bad idea

        for (ImplementsRunnableSingleProcess implementsRunnableSingleProcess : implementsRunnableSingleProcesses) {
            System.out.println(implementsRunnableSingleProcess.getRandomResult());
        }


        // another not a good idea
        List<Double> results = new ArrayList<>();
        int i;
        for (i = 0; i < 10; i++) {
            ImplementsRunnableSingleProcess implementsRunnableSingleProcess = new ImplementsRunnableSingleProcess(results);
            Thread thread = new Thread(implementsRunnableSingleProcess);
            thread.start();
        }

        long maxTime = (long) (System.nanoTime() + 1e10);
        while (results.size() < i && System.nanoTime() < maxTime) {
            Thread.sleep((long) 1e2);
            System.out.println(results);
        }
    }

}
