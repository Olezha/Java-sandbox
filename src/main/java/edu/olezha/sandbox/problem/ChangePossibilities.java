package edu.olezha.sandbox.problem;

import java.util.*;

/**
 * Your quirky boss collects rare, old coins...
 *
 * They found out you're a programmer and asked you to
 * solve something they've been wondering for a long time.
 *
 * Write a method that, given:
 * 1. an amount of money
 * 2. an array of coin denominations
 *
 * computes the number of ways to make the amount of money
 * with coins of the available denominations.
 *
 * Example: for amount=4 (4¢) and denominations=[1,2,3] (1¢, 2¢ and 3¢),
 * your program would output 4—the number of ways
 * to make 4¢ with those denominations:
 * 1. 1¢, 1¢, 1¢, 1¢
 * 2. 1¢, 1¢, 2¢
 * 3. 1¢, 3¢
 * 4. 2¢, 2¢
 */
public class ChangePossibilities {

    public static void main(String[] args) {
        System.out.println(changePossibilities(4, new int[] {1, 2, 3}, new ArrayList<>()));
    }

    // O(n^2)
    static Set<List<Integer>> changePossibilities(int amount, int[] denominations, List<Integer> prefix) {
        Set<List<Integer>> changePossibilities = new HashSet<>();

        for (int denomination : denominations) {
            int left = amount - denomination;

            if (left < 0) continue;

            List<Integer> possibility = new ArrayList<>(prefix);
            possibility.add(denomination);
            if (left == 0) {
                Collections.sort(possibility);
                changePossibilities.add(possibility);
            } else {
                changePossibilities.addAll(
                        changePossibilities(amount - denomination, denominations, possibility)
                );
            }
        }

        return changePossibilities;
    }
}
