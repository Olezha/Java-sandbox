package edu.olezha.jsandbox.memory;

import java.util.Arrays;

public class OutOfMemoryHeapSpaceSimulator {

    public static void main(String[] args) {
        int a = 32;

        try {
            System.out.println(Arrays.toString(new long[Integer.MAX_VALUE]));
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError: " + e.getMessage());
        }

        try {
            recursive();
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError");
        }

        System.out.println(a);
        System.out.println("OutOfMemoryHeapSpaceSimulator END");
    }

    private static void recursive() {
        recursive();
    }
}
