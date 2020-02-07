package edu.olezha.sandbox.algo.suffixarray;

public class LRS {

    public static void main(String[] args) {
        String text = "qwerty erty";
        int N = text.length();
        SuffixArray suffixArray = new SuffixArray(text);
        String lrs = "";
        for (int i = 1; i < N; i++) {
            int length = suffixArray.lcp(i);
            if (length > lrs.length())
                lrs = suffixArray.select(i).substring(0, length);
        }
        System.out.println(lrs);
    }
}
