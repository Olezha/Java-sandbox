package edu.olezha.sandbox.algo.suffixarray;

import java.util.Arrays;

public class SuffixArray {

    private final String[] suffixes;
    private final int N;

    public SuffixArray(String text) {
        N = text.length();
        suffixes = new String[N];
        for (int i = 0; i < N; i++)
            suffixes[i] = text.substring(i); // TODO: fix performance
        Arrays.sort(suffixes);
    }

    int length() {
        return N;
    }

    String select(int i) {
        return suffixes[i];
    }

    int index(int i) {
        return N - suffixes[i].length();
    }

    private static int lcp(String s, String t) {
        int N = Math.min(s.length(), t.length());
        for (int i = 0; i < N; i++)
            if (s.charAt(i) != t.charAt(i)) return i;
        return N;
    }

    int lcp(int i) {
        return lcp(suffixes[i], suffixes[i - 1]);
    }

    int rank(String key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(suffixes[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }
}
