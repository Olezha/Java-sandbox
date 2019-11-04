package edu.olezha.sandbox.core;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Arr {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(grouping(new int[]{1, 2, -3, 4, 4, -6})));
        log.info("{}", groupingThroughList(Arrays.asList(1, 2, -3, 4, 4, -6)));
    }

    private static int[][] grouping(int[] in) {
        int[][] res = new int[in.length][];
        int[] group = new int[in.length];
        int inGroupPointer = 0;
        int nextGroupPointer = 0;

        for (int i = 0; i < in.length; i++) {
            if (i > 0 && in[i] < in[i - 1]) {
                res[nextGroupPointer++] = group;
                group = new int[in.length];
                inGroupPointer = 0;
            }

            group[inGroupPointer++] = in[i];
        }

        if (inGroupPointer > 0)
            res[nextGroupPointer] = group;

        return res;
    }

    private static List<List<Integer>> groupingThroughList(List<Integer> in) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        for (Integer anIn : in) {
            List<Integer> group = res.get(res.size() - 1);
            if (group.size() > 0 && group.get(group.size() - 1) > anIn) {
                group = new ArrayList<>();
                res.add(group);
            }

            group.add(anIn);
        }

        return res;
    }
}
