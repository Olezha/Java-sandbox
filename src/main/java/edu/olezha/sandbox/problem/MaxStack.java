package edu.olezha.sandbox.problem;

import java.util.Stack;

public class MaxStack<E extends Comparable> extends Stack<E> {

    E getMax() {
        E max = peek();
        for (Object elenent : elementData) {
            if (elenent != null && max.compareTo(elenent) < 0)
                max = (E) elenent;
        }
        return max;
    }

    public static void main(String[] args) {
        MaxStack<Integer> data = new MaxStack<>();
        data.push(1);
        data.push(2);
        data.push(4);
        data.push(11);
        data.push(8);
        data.push(4);

        System.out.println(data.getMax());
        System.out.println(data);
    }
}
