package edu.olezha.sandbox.algo;

import java.util.*;

public class SortedList<E> implements Collection<E>, RandomAccess {

    private boolean sorted;
    private List<E> list = new ArrayList<>();

    public SortedList() {}

    public SortedList(List<E> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        sort();
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        sort();
        return list.toArray();
    }

    @Override
    public boolean add(E o) {
        sorted = false;
        return list.add(o);
    }

    public SortedList<E> put(E o) {
        sorted = false;
        list.add(o);
        return this;
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        sorted = false;
        return list.addAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return list.containsAll(c);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        sort();
        return list.toArray(a);
    }

    public E get(int index) {
        return list.get(index);
    }

    private void sort() {
        if (sorted) return;
        list.sort(null);
        sorted = true;
    }
}
