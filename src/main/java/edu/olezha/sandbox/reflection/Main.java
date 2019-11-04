package edu.olezha.sandbox.reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        Object obj = new A();

        MethodHandle mh = getTwoArgMH();
        try {
            Object ret = mh.invoke(obj, 1, 1);
            System.out.println(ret);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        // reflective
        try {
            System.out.println(makeReflective(obj).invoke(obj, 1, 1));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static MethodHandle getTwoArgMH() {
        MethodHandle mh;
        MethodType mt = MethodType.methodType(Integer.class, Integer.class, Integer.class);
        MethodHandles.Lookup lk = MethodHandles.lookup();

        try {
            mh = lk.findVirtual(A.class, "sum", mt);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw (AssertionError) new AssertionError().initCause(e);
        }

        return mh;
    }

    private static Method makeReflective(Object obj) {
        Method method = null;
        try {
            method = obj.getClass().getDeclaredMethod("sum", Integer.class, Integer.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }
}
