package edu.olezha.sandbox.problem;

/**
 * Find the longest substring without repeating characters
 * https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 */
public class LongestUniqueSubstring {

    static final int NO_OF_CHARS = 256;

    /**
     * bbbcqwertydbe
     *   ^       ^
     *   ->      ->
     *
     * Complexity: O(n + d)
     * Space: O(d)
     * where n is length of the input string
     *  and d is number of characters in input string alphabet
     */
    static String longestUniqueSubstring(String str) {
        if (str.length() == 0) return str;
        String longestSubstring = String.valueOf(str.charAt(0));

        int substringStart = 0;
        int substringLength = 1;

        int[] visited = new int[NO_OF_CHARS];
        for (int i = 0; i < NO_OF_CHARS; i++) visited[i] = -1;
        visited[str.charAt(0)] = 0;

        for (int i = 1; i < str.length(); i++) {
            if (visited[str.charAt(i)] >= substringStart) {
                if (substringLength > longestSubstring.length())
                    longestSubstring = str.substring(substringStart, substringStart + substringLength);
                substringStart = i;
                substringLength = 1;
            } else {
                substringLength++;
            }
            visited[str.charAt(i)] = i;
        }

        if (substringLength > longestSubstring.length())
            longestSubstring = str.substring(substringStart, substringStart + substringLength);

        return longestSubstring;
    }

    public static void main(String[] args) {
        System.out.println(longestUniqueSubstring("bbbcde"));
        System.out.println(longestUniqueSubstring("bbbcdbe"));
        System.out.println(longestUniqueSubstring("ABDEFGABEF"));
    }
}
