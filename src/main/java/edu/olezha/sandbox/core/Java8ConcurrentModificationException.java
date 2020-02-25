package edu.olezha.sandbox.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Java8ConcurrentModificationException {

    public static void main(String[] args) {
        System.out.println("Java " + System.getProperty("java.specification.version"));

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        try {
            for (String value : list) {
                System.out.println("List Value:" + value);
                if (value.equals("3"))
                    list.remove(value);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException");
        }

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        try {
            for (String key : map.keySet()) {
                System.out.println("Map Value:" + map.get(key));
                if (key.equals("2")) {
                    map.put("1", "4");
                    map.put("4", "4");
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException");
        }
        System.out.println(map);

        List<String> list2 = new CopyOnWriteArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");

        for (String value : list2) {
            System.out.println("List Value:" + value);
            if (value.equals("3")) {
                list2.remove("4");
                list2.add("6");
                list2.add("7");
            }
        }
        System.out.println("List Size:" + list2.size());

        Map<String, String> map2 = new ConcurrentHashMap<>();
        map2.put("1", "1");
        map2.put("2", "2");
        map2.put("3", "3");

        for (String key : map2.keySet()) {
            System.out.println("Map Value:" + map2.get(key));
            if (key.equals("1")) {
                map2.remove("3");
                map2.put("4", "4");
                map2.put("5", "5");
            }
        }
        System.out.println("Map Size:" + map2.size());
    }
}
