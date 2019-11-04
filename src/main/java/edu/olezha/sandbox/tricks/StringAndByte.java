package edu.olezha.sandbox.tricks;

import java.util.Arrays;

public class StringAndByte {

    public static void main(String[] args) throws Exception {
        char[] chars = new char[]{'\u0097'};
        String str = new String(chars);
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));
    }
}
