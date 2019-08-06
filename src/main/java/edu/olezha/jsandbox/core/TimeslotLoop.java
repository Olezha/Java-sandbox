package edu.olezha.jsandbox.core;

import java.util.concurrent.TimeUnit;

public class TimeslotLoop {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        int i = 0;
        long timeLeft = TimeUnit.SECONDS.toMillis(15);
        long startedAt;
        for (long timeSpent = 0; timeLeft - timeSpent > 0; timeSpent = System.currentTimeMillis() - startedAt, timeLeft -= timeSpent) {
            startedAt = System.currentTimeMillis();

            System.out.println("work " + i++);
            synchronized (object) {
                object.wait(TimeUnit.SECONDS.toMillis(1));
            }
        }
        System.out.println("finish");
    }
}
