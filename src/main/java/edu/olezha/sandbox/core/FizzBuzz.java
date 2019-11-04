package edu.olezha.sandbox.core;

/*
 * Write a program that
 * - print the numbers from 1 to 100
 * - But for multiples of the three print "Fizz" instead of the number
 * - and for the multiplies of five print "Buzz".
 * - For numbers which are miltiples of both three and five print "FizzBuzz".
 */
public class FizzBuzz {

    public static void main(String[] args) {
        fizzBuzzV1(1, 100);
        fizzBuzzCorrect(1, 100);
    }

    /**
     * v1
     */
    private static void fizzBuzzV1(int from, int to) {
        for (int i = from; i <= to; i++) {
            if (i % 3 == 0) {
                if (i % 5 == 0) {
                    System.out.println("FizzBuzz");
                    continue;
                }
                System.out.println("Fizz");
                continue;
            }
            if (i % 5 == 0) {
                System.out.println("Buzz");
                continue;
            }
            System.out.println(i);
        }
    }

    /**
     * (http://wiki.c2.com/?FizzBuzzTest)
     */
    private static void fizzBuzzCorrect(int from, int to) {
        boolean flag = true;

        for (int i = from; i <= to; i++) {
            if (i % 3 == 0) {
                System.out.print("Fizz");
                flag = false;
            }

            if (i % 5 == 0) {
                System.out.print("Buzz");
                flag = false;
            }

            if (flag)
                System.out.print(i);

            System.out.println("");

            flag = true;
        }
    }
}
