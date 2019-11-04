package edu.olezha.sandbox.java8.intf;

/**
 * Created by oleh on 12.11.16.
 */
public interface Some {

    static String staticMethod() {
        return "Interfaces static method";
    }

    default String defaultMethod() {
        return "Interfaces default method";
    }

}
