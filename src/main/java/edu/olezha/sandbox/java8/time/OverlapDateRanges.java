package edu.olezha.sandbox.java8.time;

import java.time.LocalDate;

public class OverlapDateRanges {

    public static void main(String[] args) {
        LocalDate from1 = LocalDate.of(2020, 8, 20);
        LocalDate to1 = LocalDate.of(2020, 8, 24);
        LocalDate from2 = LocalDate.of(2020, 8, 25);
        LocalDate to2 = LocalDate.of(2020, 8, 26);
        System.out.println(checkOverlap(from1, to1, from2, to2));
    }

    // (from1 <= to2) and (from2 <= to1)
    // "before or equal" = "not after"
    private static boolean checkOverlap(LocalDate from1, LocalDate to1, LocalDate from2, LocalDate to2) {
        return !from1.isAfter(to2) && !from2.isAfter(to1);
    }
}
