package edu.olezha.sandbox.algo.suffixarray;

public class KWIC { // keyword-in-context

    public static void main(String[] args) {
        String text = "qwerty";
        int N = text.length();
        SuffixArray suffixArray = new SuffixArray(text);

        int context = 3;
        String[] lines = {
                "berty",
                "ty",
                "went"
        };

        for (String q : lines) {
            for (int i = suffixArray.rank(q); i < N && suffixArray.select(i).startsWith(q); i++) {
                int from = Math.max(0, suffixArray.index(i) - context);
                int to = Math.min(N-1, from + q.length() + 2 * context);
                System.out.println(text.substring(from, to));
            }
            System.out.println();
        }
    }
}
