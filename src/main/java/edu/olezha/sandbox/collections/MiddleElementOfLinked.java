package edu.olezha.sandbox.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Finding Middle element of linked list in one pass
 */
public class MiddleElementOfLinked {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(middle(list));
    }

    private static <E> E middle(List<E> list) {
        if (list.size() == 0)
            return null;

        int middle = (list.size() - 1) / 2;
        Iterator<E> iterator = list.iterator();
        for (int i = 0; i < middle; i++)
            iterator.next();
        return iterator.next();
    }
}
