package edu.olezha.sandbox.collections;

import java.util.Random;

public class ArrayBinaryHeapApp {

    public static void main(String[] args) {
        ArrayBinaryHeap arrayBinaryHeap = new ArrayBinaryHeap();
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            arrayBinaryHeap.add(random.nextInt(128));
            System.out.println(arrayBinaryHeap);
        }

        while (arrayBinaryHeap.size() > 0) {
            System.out.println(arrayBinaryHeap.get() + " " + arrayBinaryHeap);
        }
    }
}
