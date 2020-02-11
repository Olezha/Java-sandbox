package edu.olezha.sandbox.problem;

/**
 * https://code.google.com/codejam/contest/3264486/dashboard
 */
public class PancakeFlipper {

    public static void main(String[] args) {
        System.out.println(view(1, solve(parsePancakes("---+-++-"), 3)));
        System.out.println(view(2, solve(parsePancakes("+++++"), 4)));
        System.out.println(view(3, solve(parsePancakes("-+-+-"), 4)));
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
