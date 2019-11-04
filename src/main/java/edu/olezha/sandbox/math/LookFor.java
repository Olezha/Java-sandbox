package edu.olezha.sandbox.math;

public class LookFor {

    public static void main(String[] args) {
//        System.out.println(lookFor(10));
//        System.out.println(lookFor(0));
        System.out.println(lookFor(-10));
    }

    private static int lookFor(int max) {
        int a = 1;
        while (a > 0)
            a++;
        int b = 1;
        while (b > 0)
            b++;
        int c = a * a + b * b;
        while (c >= a * a + b * b)
            c = max;
        return max;
    }
}
