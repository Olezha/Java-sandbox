package edu.olezha.sandbox.problem;

public class RecursiveStaircase {

    static int numWays(int N, int[] possibleSteps) {
        if (N < 0) throw new IllegalArgumentException();

        int[] numWays = new int[N + 1];
        numWays[0] = 1;
        for (int i = 1; i < numWays.length; i++)
            for (int possibleStep : possibleSteps)
                if (i - possibleStep >= 0)
                    numWays[i] += numWays[i - possibleStep];

        return numWays[N];
    }

    public static void main(String[] args) {
        System.out.println(numWays(4, new int[] {1, 2}));
        System.out.println(numWays(4, new int[] {1, 3, 5}));
        System.out.println(numWays(2, new int[] {}));
        System.out.println(numWays(1, new int[] {}));
    }
}
