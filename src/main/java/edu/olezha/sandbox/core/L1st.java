package edu.olezha.sandbox.core;

import java.util.ArrayList;
import java.util.List;

public class L1st {

    public static void main(String[] args) {
        List<Object> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(1.0);
        longs.add("Long");
        longs.add(new Person(0L, null));
        System.out.println(longs.size());
    }
}
