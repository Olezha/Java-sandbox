package edu.olezha.sandbox;

public class Time {

    public static void main(String[] args) {
        runWithSpin();
    }

    private static void runWithSpin() {
        long nowNanos = 0, startNanos = 0;
        long nowMillis, startMillis = nowMillis = System.currentTimeMillis();

        while (startMillis == nowMillis) {
            startNanos = System.nanoTime();
            nowMillis = System.currentTimeMillis();
        }

        startMillis = nowMillis;
        double maxDrift = 0;
        for (int i = 0; i < 100; i++) {
            long lastMillis = nowMillis;
            while (nowMillis - lastMillis < 1_000) {
                nowNanos = System.nanoTime();
                nowMillis = System.currentTimeMillis();
            }

            long durationMillis = nowMillis - startMillis;
            double driftNanos = Math.abs(1_000_000 * (((double) (nowNanos - startNanos)) / 1_000_000 - durationMillis));
            if (driftNanos > maxDrift) {
                maxDrift = driftNanos;
                System.out.printf("Millis: %6d; drift nanos: %f" + System.lineSeparator(), durationMillis, driftNanos);
            }
        }
    }
}
