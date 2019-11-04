package edu.olezha.sandbox.xyz;

public class A {
    public static class X {
        public static class Y {
            public static String Z = "life is good";
        }

        public static C Y;
    }

    public static class C {
        public static String Z = "life is plain";
    }

    public static void main(String[] args) {
        System.out.println(X.Y.Z);
    }
}
