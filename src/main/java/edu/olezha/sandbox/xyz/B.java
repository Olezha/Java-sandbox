package edu.olezha.sandbox.xyz;

public class B {
    public static <T> T foo() {
        try {
            return (T) new Integer(42);
        } catch (ClassCastException e) {
            return (T) null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(B.<String>foo());
    }
}
