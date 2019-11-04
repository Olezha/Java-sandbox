package edu.olezha.sandbox.core.bit;

public class EvenOrOdd {

    public static void main(String[] args) {
        for (int i = -10; i < 20; i++) {
            if ((i & 1) == 0)
                System.out.println(i + " is even");
            else
                System.out.println(i + " is odd");
        }
    }
}
