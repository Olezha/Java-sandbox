package edu.olezha.sandbox.multithreading.forkjoin;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MicroBlogUpdateSorter extends RecursiveAction {

    public static void main(String[] args) {
        List<Update> updates = new ArrayList<>();
        String text = "";
        for (int i = 0; i < 256; i++) {
            text += "x";
            Update update = new Update();
            update.setText(text);
            updates.add(update);
        }
        Collections.shuffle(updates);


        Update[] u = updates.toArray(new Update[0]); // stackoverflow.com/questions/174093
        MicroBlogUpdateSorter sorter = new MicroBlogUpdateSorter(u);
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(sorter);

        for (Update update : sorter.getResult())
            System.out.println(update);
    }

    private static final int SMALL_ENOUGH = 32;
    private final Update[] updates;
    private final int start, end;
    private final Update[] result;

    private MicroBlogUpdateSorter(Update[] updates) {
        this(updates, 0, updates.length);
    }

    private MicroBlogUpdateSorter(Update[] updates, int start, int end) {
        this.updates = updates;
        this.start = start;
        this.end = end;
        result = new Update[updates.length];
    }

    private void merge(MicroBlogUpdateSorter left, MicroBlogUpdateSorter right) {
        int i = 0;
        int lCt = 0;
        int rCt = 0;

        while (lCt < left.size() && rCt < right.size())
            result[i++] = left.result[lCt].compareTo(right.result[rCt]) < 0 ? left.result[lCt++] : right.result[rCt++];

        while (lCt < left.size())
            result[i++] = left.result[lCt++];

        while (rCt < right.size())
            result[i++] = right.result[rCt++];
    }

    private int size() {
        return end - start;
    }

    private Update[] getResult() {
        return result;
    }

    @Override
    protected void compute() {
        if (size() < SMALL_ENOUGH) {
            System.arraycopy(updates, start, result, 0, size());
            Arrays.sort(result, 0, size());
        } else {
            int mid = size() / 2;
            MicroBlogUpdateSorter left = new MicroBlogUpdateSorter(updates, start, start + mid);
            MicroBlogUpdateSorter right = new MicroBlogUpdateSorter(updates, start + mid, end);
            invokeAll(left, right);
            merge(left, right);
        }
    }
}

@Data
class Update implements Comparable<Update> {

    private String text;

    @Override
    public int compareTo(Update o) {
        return getText().compareTo(o.getText());
    }
}
