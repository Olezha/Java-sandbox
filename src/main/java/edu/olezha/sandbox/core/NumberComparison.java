package edu.olezha.sandbox.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberComparison {

    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("21.0", "1.0", "2.0", "te.xt", "", "3.2", "3.0", "3.1", "0.0");
        System.out.println(numbers.stream().sorted((d1, d2) -> {
            int d1Major, d1Minor, d2Major, d2Minor;
            try {
                String[] parts = d1.split("\\.");
                d1Major = Integer.parseInt(parts[0]);
                d1Minor = Integer.parseInt(parts[1]);
            } catch (RuntimeException e) {
                return 1;
            }
            try {
                String[] parts = d2.split("\\.");
                d2Major = Integer.parseInt(parts[0]);
                d2Minor = Integer.parseInt(parts[1]);
            } catch (RuntimeException e) {
                return -1;
            }
            if (d1Major == d2Major)
                return d1Minor - d2Minor;
            return d1Major - d2Major;
        }).collect(Collectors.toList()));
    }
}
