package edu.olezha.sandbox.algo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class SortAndCountDifference<T extends Comparable<T>> {

    public static void main(String[] args) {
        System.out.println(new SortAndCountDifference<Integer>().sortAndCount(
                new L1st<>(new ArrayList<Integer>() {{
            add(2);
            add(4);
            add(1);
            add(3);
            add(5);
        }})));
    }

    public L1st<T> sortAndCount(L1st<T> in) {
        if (in.list.size() == 1) {
            return in;
        }

        L1st<T> a = new L1st<>(
                in.list.subList(0, in.list.size() / 2 + in.list.size() % 2));
        a = sortAndCount(a);

        L1st<T> b = new L1st<>(
                in.list.subList(in.list.size() / 2 + in.list.size() % 2, in.list.size()));
        b = sortAndCount(b);

        return mergeAndCount(a, b);
    }

    private L1st<T> mergeAndCount(L1st<T> a, L1st<T> b) {
        L1st<T> result = new L1st<>();
        result.inversions = a.inversions + b.inversions;

        int i = 0, j = 0;
        while (a.list.size() > i && b.list.size() > j) {
            T ai = a.list.get(i);
            T bj = b.list.get(j);

            if (bj.compareTo(ai) < 0) {
                j++;
                result.inversions += a.list.size() - i;
                result.list.add(bj);
            } else {
                i++;
                result.list.add(ai);
            }
        }

        if (i < a.list.size()) {
            for (; a.list.size() > i; i++) {
                result.list.add(a.list.get(i));
            }
        }
        if (j < b.list.size()) {
            for (; b.list.size() > j; j++) {
                result.list.add(b.list.get(j));
            }
        }

        return result;
    }
}

@Data
class L1st<T extends Comparable<T>> {
    List<T> list;
    int inversions;

    L1st() {
        this.list = new ArrayList<>();
    }

    L1st(List<T> in) {
        this.list = in;
    }
}
