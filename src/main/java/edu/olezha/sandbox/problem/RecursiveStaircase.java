package edu.olezha.sandbox.problem;

public class RecursiveStaircase {

    static int numWays(int N, int[] possibleSteps) {
        if (N < 0) return 0;
        if (N < 2) return 1;

        int numWays = 0;
        for (int possibleStep : possibleSteps)
            numWays += numWays(N - possibleStep, possibleSteps);

        return numWays;
    }

    public static void main(String[] args) {
        System.out.println(numWays(4, new int[] {1, 2}));
        System.out.println(numWays(4, new int[] {1, 3, 5}));
    }
}
