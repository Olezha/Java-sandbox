package edu.olezha.sandbox.problem;

public class RecursiveStaircase {

    static int numWays(int N) {
        if (N < 0) throw new IllegalArgumentException();

        if (N < 2) return 1;

        return numWays(N - 1) + numWays(N - 2);
    }

    public static void main(String[] args) {
        System.out.println(numWays(4));
    }
}
