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

        int longestSubstringFrom = 0;
        int longestSubstringLength = 1;

        int substringFrom = 0;
        int substringLength = 1;

        int[] visited = new int[NO_OF_CHARS];
        for (int i = 0; i < NO_OF_CHARS; i++) visited[i] = -1;
        visited[str.charAt(0)] = 0;

        for (int i = 1; i < str.length(); i++) {
            if (visited[str.charAt(i)] >= substringFrom) {
                if (substringLength > longestSubstringLength) {
                    longestSubstringFrom = substringFrom;
                    longestSubstringLength = substringLength;
                }
                substringLength -= visited[str.charAt(i)] - substringFrom;
                substringFrom = visited[str.charAt(i)] + 1;
            } else {
                substringLength++;
            }
            visited[str.charAt(i)] = i;
        }

        if (substringLength > longestSubstringLength) {
            longestSubstringFrom = substringFrom;
            longestSubstringLength = substringLength;
        }

        return str.substring(longestSubstringFrom, longestSubstringFrom + longestSubstringLength);
    }

    public static void main(String[] args) {
        System.out.println(longestUniqueSubstring("abcade")); // bcade
        System.out.println(longestUniqueSubstring("bbbcde")); // bcde
        System.out.println(longestUniqueSubstring("bbbcdbe")); // cbcd
        System.out.println(longestUniqueSubstring("ABDEFGABEF")); // ABDEFG
    }
}
