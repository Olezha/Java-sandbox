package edu.olezha.sandbox.problem;

/**
 * Given an range of numbers count the numbers which have same first and last digits
 * Ex. Between 7 and 20 such numbers are 8, 9 and 11
 */
public class SameFirstAndLastDigits {

    public static void main(String[] args) {
        System.out.println(countNumbersWithSameDigits(7, 20));
    }

    // O(n)
    static int countNumbersWithSameDigits(int from, int to) {
        int counter = 0;
        for (int i = from + 1; i < to; i++) {
            String number = Integer.toString(i);
            if (number.charAt(0) == number.charAt(number.length() - 1))
                counter++;
        }
        return counter;
    }
}
