package edu.olezha.sandbox.problem;

import java.util.Arrays;

/**
 * https://code.google.com/codejam/contest/5304486/dashboard
 */
public class AlphabetCake {

    static void fill(char[][] cake) {
        for (int i = 0; i < cake.length; i++) {
            if (cake[i][0] == '?')
                for (int j = 1; j < cake[i].length; j++)  // check first letter in line
                    if (cake[i][j] != '?') { // if there is a letter
                        cake[i][0] = cake[i][j]; // fill in the first position
                        break;
                    }
            if (cake[i][0] == '?') continue; // skip line without any letters

            // fill the entire line with the previous letter
            for (int j = 1; j < cake[i].length; j++)
                if (cake[i][j] == '?')
                    cake[i][j] = cake[i][j - 1];
        }

        if (cake[0][0] == '?') // if first line without any letters
            for (int i = 1; i < cake.length; i++)
                if (cake[i][0] != '?') cake[0] = cake[i]; // fill from first one with letters

        for (int i = 1; i < cake.length; i++)
            if (cake[i][0] == '?') // if line without letters
                cake[i] = cake[i - 1]; // fill in from previous line
    }

    public static void main(String[] args) {
        char[][] cake = {
                {'G', '?', '?'},
                {'?', 'C', '?'},
                {'?', '?', 'J'}
        };
        fill(cake);
        for (char[] line : cake)
            System.out.println(Arrays.toString(line));
    }
}
