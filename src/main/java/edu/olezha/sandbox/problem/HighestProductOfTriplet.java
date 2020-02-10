package edu.olezha.sandbox.problem;

public class HighestProductOfTriplet {

    public static void main(String[] args) {
        System.out.println("1200 " + highestProductOfTriplet(new int[] {10, 3, 5, 6, 20}));
        System.out.println("-90 " + highestProductOfTriplet(new int[] {-10, -3, -5, -6, -20}));
        System.out.println("168 " + highestProductOfTriplet(new int[] {1, -4, 3, -6, 7, 0}));
    }

    // O(n)
    static int highestProductOfTriplet(int[] a) {
        int h1 = Integer.MIN_VALUE, h2 = Integer.MIN_VALUE, h3 = Integer.MIN_VALUE,
                l1 = Integer.MAX_VALUE, l2 = Integer.MAX_VALUE;
        for (int e : a) {
            if (e > h3) {
                if (e > h2) {
                    if (e > h1) {
                        h3 = h2;
                        h2 = h1;
                        h1 = e;
                    } else {
                        h3 = h2;
                        h2 = e;
                    }
                } else {
                    h3 = e;
                }
            }

            if (e < l2) {
                if (e < l1) {
                    l2 = l1;
                    l1 = e;
                } else {
                    l2 = e;
                }
            }
        }

        int max1 = h1 * h2 * h3;
        int max2 = l1 * l2 * h1;

        return Math.max(max1, max2);
    }
}
