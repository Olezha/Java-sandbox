package edu.olezha.jsandbox.multithreading.concurrent;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple RNG
 * useful for tests
 */
public class RNG {

    public static void main(String[] args) {
        System.out.println(xorShift((int) System.nanoTime()));
        System.out.println(xorShift((int) System.nanoTime()));
        System.out.println(xorShift((int) System.nanoTime()));
        System.out.println(xorShift((int) System.nanoTime()));

        System.out.println(xorShift(new Object().hashCode()));
        System.out.println(xorShift(new Object().hashCode()));
        System.out.println(xorShift(new Object().hashCode()));
        System.out.println(xorShift(new Object().hashCode()));

        Set<Integer> numbers = new HashSet<>();
        int ITERATIONS = 20_000_000;
        int duplicates = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            if (!numbers.add(xorShift((int) System.nanoTime())))
                duplicates++;

            if (ITERATIONS / 2 == i)
                System.out.println((double) duplicates * 100 / ITERATIONS);
        }
        System.out.println((double) duplicates * 100 / ITERATIONS);
    }

    public static int random() {
        return xorShift((int) System.nanoTime());
    }

    private static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }
}
