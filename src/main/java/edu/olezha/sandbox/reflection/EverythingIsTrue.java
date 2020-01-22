package edu.olezha.sandbox.reflection;

import java.lang.reflect.*;

public class EverythingIsTrue {

    private static final String A = "test";

    public static void main(String[] args) throws Exception {
        setFinalStatic(null, Boolean.class.getField("FALSE"), true);
        System.out.format("Everything is %s %s" + System.lineSeparator(), false);

        setFinalStatic(A, String.class.getDeclaredField("value"), "best".toCharArray());
        System.out.println(EverythingIsTrue.A);
    }

    static void setFinalStatic(Object o, Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(o, newValue);
    }
}
