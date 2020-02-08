package edu.olezha.sandbox.problem;

public class RecursiveStaircase {

    static int numWays(int N) {
        if (N < 0) throw new IllegalArgumentException();
        if (N < 2) return 1;

        int last = 1;
        int oneBeforeLast = 1;

        for (int i = 2; i <= N; i++) {
            int next = last + oneBeforeLast;
            oneBeforeLast = last;
            last = next;
        }

        return last;
    }

    public static void main(String[] args) {
        System.out.println(numWays(4));
    }
}
