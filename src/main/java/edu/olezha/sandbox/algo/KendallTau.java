package edu.olezha.sandbox.algo;

import java.util.Arrays;

public class KendallTau {

    public static void main(String[] args) {
        System.out.println(distance("adbgcfe", "badgecf"));
    }

    public static int distance(String a, String b) {
        if (a.length() != b.length())
            throw new IllegalArgumentException();

        char[] al = a.toCharArray();
        Arrays.sort(al);
        String alphabet = String.valueOf(al);

        int n = a.length();

        int[] ai = new int[n];
        int[] bi = new int[n];
        for (int i = 0; i < n; i++) {
            ai[i] = alphabet.indexOf(a.charAt(i));
            bi[i] = alphabet.indexOf(b.charAt(i));

            if (bi[i] == -1)
                throw new IllegalArgumentException();
        }

        int[] aInv = new int[n];
        for (int i = 0; i < n; i++)
            aInv[ai[i]] = i;

        int tau = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (aInv[bi[i]] > aInv[bi[j]])
                    tau++;

        return tau;
    }
}
