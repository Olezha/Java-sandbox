package edu.olezha.sandbox.core.bit;

public class IsNthBitIsSet {

    public static void main(String[] args) {
        int n = 4;
        for (int i = 500; i < 550; i++) {
            if ((i & (1<<n)) != 0)
                System.out.println(Integer.toBinaryString(i) + " " + n + "-th bit is set");
            else
                System.out.println(Integer.toBinaryString(i) + " " + n + "-th bit is not set");
        }
    }
}
