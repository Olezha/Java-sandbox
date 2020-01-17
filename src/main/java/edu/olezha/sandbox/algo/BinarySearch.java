package edu.olezha.sandbox.algo;

public class BinarySearch<E extends Comparable<E>> {

    public E rank(E key, SortedList<E> list) {
        int lo = 0;
        int hi = list.size() - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            E v = list.get(mid);
            int comparison = key.compareTo(v);
            if (comparison == 0) return v;
            if (comparison < 0) hi = mid - 1;
            else lo = mid + 1;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new BinarySearch<Integer>().rank(2, new SortedList<Integer>()
                .put(1).put(2).put(3).put(1).put(4).put(5).put(6).put(7).put(8).put(1).put(1).put(9)));
    }
}
