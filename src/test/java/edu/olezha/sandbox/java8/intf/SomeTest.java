package edu.olezha.sandbox.java8.intf;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by oleh on 12.11.16.
 */
public class SomeTest {

    @Test
    public void testStatic() {
        assertEquals("Interfaces static method", Some.staticMethod());
    }

    @Test
    public void testDefault() {
        Some some = new Some() {};
        assertEquals("Interfaces default method", some.defaultMethod());
    }

}
