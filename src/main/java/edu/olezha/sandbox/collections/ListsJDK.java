package edu.olezha.sandbox.collections;

import java.util.*;

/**
 * Created by oleh on 12.11.16.
 */
public class ListsJDK {

    public static void main(String[] args) {
        final int limit = (int) 1e3;

        List<Object> list = new ArrayList<>();

        for (int i = 0; i < limit; i++)
            list.add("Item" + i);

        List<Object> arrayList = new ArrayList<>();
        arrayList.addAll(list);

        List<Object> linkedList = new LinkedList<>();
        linkedList.addAll(arrayList);

        System.out.println("Time");
        System.out.println("[Iterator.remove()] arrayListTime: " + iteratingOverListAndRemove(arrayList)
                + "; linkedListTime: " + iteratingOverListAndRemove(linkedList));

        arrayList.clear();
        arrayList.addAll(list);
        linkedList.clear();
        linkedList.addAll(list);
        System.out.println("[remove(index)] arrayListTime: " + removingElementsByIndex(arrayList)
                + "; linkedListTime: " + removingElementsByIndex(linkedList));

        arrayList.clear();
        linkedList.clear();
        System.out.println("[add(E)] arrayListTime: " + addingElementsToList(arrayList, limit)
                + "; linkedListTime: " + addingElementsToList(linkedList, limit));

        arrayList.clear();
        linkedList.clear();
        System.out.println("[add(index, E)] arrayListTime: " + addingElementsToListByIndex(arrayList, limit)
                + "; linkedListTime: " + addingElementsToListByIndex(linkedList, limit));

        arrayList.clear();
        arrayList.addAll(list);
        linkedList.clear();
        linkedList.addAll(list);
        System.out.println("[Iterator.add(E)] arrayListTime: " + iteratingOverListAndAdd(arrayList)
                + "; linkedListTime: " + iteratingOverListAndAdd(linkedList));

        arrayList.clear();
        arrayList.addAll(list);
        linkedList.clear();
        linkedList.addAll(list);

        System.out.println("[get(index)] arrayListTime: " + getFromListByIndex(arrayList)
                + "; linkedListTime: " + getFromListByIndex(linkedList));


        long start = System.currentTimeMillis();
        String[] array = arrayList.toArray(new String[0]);
        long finish = System.currentTimeMillis();
        long arrayListToArrayTime = finish - start;

        start = System.currentTimeMillis();
        List<String> listFromArray = Arrays.asList(array);
        finish = System.currentTimeMillis();
        long arrayToListTime = finish - start;

        int listSize = listFromArray.size();

        System.out.println("arrayList(" + listSize + ") to array time: " + arrayListToArrayTime + "; array to list time: " + arrayToListTime);

        start = System.currentTimeMillis();
        arrayList = new ArrayList<>();
        for (int i = 0; i < limit; i++)
            arrayList.add("Item" + i);
        finish = System.currentTimeMillis();
        long arrayListTime = finish - start;

        start = System.currentTimeMillis();
        array = new String[limit];
        for (int i = 0; i < limit; i++)
            array[i] = "Item" + i;
        finish = System.currentTimeMillis();
        long arrayTime = finish - start;

        System.out.println("Create arrayList[" + limit + "] time: " + arrayListTime + "; create array time: " + arrayTime);


        @SuppressWarnings("unchecked")
        List<String> shortList = (List<String>)(List<?>) list.subList(0, 15);
        System.out.println("\nCompare\n" + shortList);
        Collections.sort(shortList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int o1ItemNumber = Integer.parseInt(o1.substring(o1.length() - 1));
                int o2ItemNumber = Integer.parseInt(o2.substring(o2.length() - 1));

                return o1ItemNumber - o2ItemNumber;
            }
        });
        System.out.println(shortList);
    }

    public static long iteratingOverListAndRemove(List<Object> list) {
        Iterator<Object> iterator = list.iterator();

        long startTime = System.currentTimeMillis();

        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

        return System.currentTimeMillis() - startTime;
    }

    public static long removingElementsByIndex(List<Object> list) {
        int halfSize = list.size() / 2;

        long startTime = System.currentTimeMillis();

        for (int i = halfSize; i < list.size(); i++)
            list.remove(halfSize);

        for (int i = 0; i < list.size(); i++)
            list.remove(i);

        return System.currentTimeMillis() - startTime;
    }

    public static long addingElementsToList(List<Object> list, int limit) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < limit; i++)
            list.add("Item" + i);

        return System.currentTimeMillis() - startTime;
    }

    public static long addingElementsToListByIndex(List<Object> list, int limit) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < limit; i++)
            list.add(list.size() / 2, "Item" + i);

        return System.currentTimeMillis() - startTime;
    }

    public static long iteratingOverListAndAdd(List<Object> list) {
        int i = 0;

        ListIterator<Object> listIterator = list.listIterator();

        long startTime = System.currentTimeMillis();

        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.add("" + i++);
        }

        return System.currentTimeMillis() - startTime;
    }

    public static long getFromListByIndex(List<Object> list) {
        int halfSize = list.size() / 2;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < halfSize - 1; i++) {
            list.get(halfSize + i);
            list.get(halfSize - i);
        }

        return System.currentTimeMillis() - startTime;
    }

}
