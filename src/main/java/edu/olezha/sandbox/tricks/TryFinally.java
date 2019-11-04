package edu.olezha.sandbox.tricks;

public class TryFinally {

    public static void main(String[] args) {
        try {
            rtn();
            System.exit(0);
        }
        finally {
            System.out.println("main");
        }
    }

    private static void rtn() {
        try {
            return;
        }
        finally {
            System.out.println("return");
        }
    }
}
