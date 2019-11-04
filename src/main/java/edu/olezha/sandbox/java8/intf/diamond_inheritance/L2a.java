package edu.olezha.sandbox.java8.intf.diamond_inheritance;

public interface L2a extends L1 {

    default String someMethod() {
        return "L2a";
    };
}
