package edu.olezha.sandbox.problem;

import java.util.Arrays;

public class RevStrByWord {

    public static void main(String[] args) {
        System.out.println(rev("My name is Oleh"));
    }

    static String rev(String s) {
        String[] words = s.split(" ");
        for (int i = 0; i < words.length / 2; i++) {
            String sb = words[i];
            words[i] = words[words.length - 1 - i];
            words[words.length - 1 - i] = sb;
        }
        return String.join(" ", words);
    }
}
