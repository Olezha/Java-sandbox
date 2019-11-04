package edu.olezha.sandbox.java8.intf.diamond_inheritance;

public class L3 implements L2a, L2b {

    public String someMethod() {
        return "L3";
    };

    public static void main(String[] args) {
        L3 l3 = new L3();
        System.out.println(l3.someMethod());
    }
}
