package edu.olezha.sandbox.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Finding Middle element of linked list in ~~one~~ 1.5 pass
 * and you don’t know the length of LinkedList
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
        System.out.println(list.get((list.size() - 1)/2));
    }

    private static <E> E middle(List<E> list) {
        E mid = null;

        Iterator<E> slowIterator = list.iterator();
        Iterator<E> fastIterator = list.iterator();
        while (fastIterator.hasNext()) {
            mid = slowIterator.next();

            fastIterator.next();
            if (fastIterator.hasNext()) fastIterator.next();
        }

        return mid;
    }
}
