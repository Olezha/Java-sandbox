package edu.olezha.sandbox.problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://code.google.com/codejam/contest/3264486/dashboard
 */
public class PancakeFlipper {

    public static void main(String[] args) throws IOException {
        test("A-small-practice.in");
        test("A-large-practice.in");
    }

    static void test(String file) throws IOException {
        try (BufferedReader bufferedReader =
                     new BufferedReader(
                             new FileReader(
                                     PancakeFlipper.class.getClassLoader().getResource(
                                             file).getFile()))) {
            bufferedReader.readLine();
            String line;
            int i = 1;
            while ((line = bufferedReader.readLine()) != null) {
                String[] in = line.split(" ");
                System.out.println(view(i++, solve(parsePancakes(in[0]), Integer.parseInt(in[1]))));
            }
        }
    }

    static int solve(boolean[] pancakes, int flipperSize) {
        int flips = 0;

        for (int i = 0; i < pancakes.length; i++) {
            if (!pancakes[i]) {
                if (i + flipperSize <= pancakes.length) {
                    for (int j = i; j < i + flipperSize; j++) {
                        pancakes[j] = !pancakes[j];
                    }
                    flips++;
                } else {
                    return -1;
                }
            }
        }

        return flips;
    }

    static boolean[] parsePancakes(String s) {
        boolean[] result = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++)
            result[i] = s.charAt(i) == '+';
        return result;
    }

    static String view(int n, int flips) {
        return "Case #" + n + ": " + ((flips >= 0) ? flips : "IMPOSSIBLE");
    }
}
