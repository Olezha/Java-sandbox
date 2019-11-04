package edu.olezha.sandbox.multithreading.concurrent;

import java.util.concurrent.atomic.AtomicReference;

public class CompareAndSet {

    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0, 5));

    public int getLower() {
        return values.get().lower;
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldV = values.get();
            if (i > oldV.upper)
                throw new IllegalArgumentException(
                        "Can’t set lower to " + i + " > upper");
            IntPair newV = new IntPair(i, oldV.upper);

            if (values.compareAndSet(oldV, newV))
                return;
        }
    }

    public static void main(String[] args) {
        CompareAndSet compareAndSet = new CompareAndSet();
        compareAndSet.setLower(2);
        assert compareAndSet.getLower() == 2;
        compareAndSet.setLower(1);
    }

    private static class IntPair {

        final int lower; // Invariant: lower <= upper

        final int upper;

        IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }
}
