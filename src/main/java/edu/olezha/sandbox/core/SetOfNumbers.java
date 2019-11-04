package edu.olezha.sandbox.core;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetOfNumbers {

    public static void main(String[] args) {
        Set<Number> numbers = new TreeSet<>();
        numbers.add(1);
        numbers.add(1L);
        numbers.add(1.0);
        System.out.println(numbers.size());

        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1L);
        numbers.add(1.0);
        System.out.println(numbers.size());
    }
}
