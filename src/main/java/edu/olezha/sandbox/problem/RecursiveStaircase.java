package edu.olezha.sandbox.problem;

public class RecursiveStaircase {

    static int numWays(int N) {
        if (N < 0) throw new IllegalArgumentException();
        if (N < 2) return 1;

        int[] numWays = new int[N + 1];
        numWays[0] = numWays[1] = 1;
        for (int i = 2; i < numWays.length; i++)
            numWays[i] = numWays[i - 1] + numWays[i - 2];

        return numWays[N];
    }

    public static void main(String[] args) {
        System.out.println(numWays(4));
    }
}
