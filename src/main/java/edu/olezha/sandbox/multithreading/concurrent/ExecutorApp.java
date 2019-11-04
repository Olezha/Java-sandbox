package edu.olezha.sandbox.multithreading.concurrent;

import edu.olezha.sandbox.multithreading.lang.ImplementsRunnableSingleProcess;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by oleh on 13.11.16.
 */
public class ExecutorApp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(new ImplementsRunnableSingleProcess());
        System.out.println("ImplementsRunnableSingleProcess is done: " + future.isDone());

        try {
            System.out.println("before get");
            future.get();
            System.out.println("after get\n");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Future<String> callableFuture = executorService.submit(new ImplementsCallableSingleProcess());
        try {
            System.out.println("before get");
            System.out.println(callableFuture.get());
            System.out.println("after get");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        executorService.shutdown();
    }

}
