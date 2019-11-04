package edu.olezha.sandbox.core;

public class LongValueOf {

    public static void main(String[] args) {
        Long l1 = 111L;
        Long l2 = 111L;
        Long l3 = 222L;
        Long l4 = 222L;

        System.out.println(l1 == l2);
        System.out.println(l3 == l4);
    }
}
