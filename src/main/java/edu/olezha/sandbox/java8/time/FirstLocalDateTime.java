package edu.olezha.sandbox.java8.time;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Slf4j
public class FirstLocalDateTime {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        log.info("Now:               {}", now);
        log.info("At start of Day:   {}", atStartOfDay(now));
        log.info("At start of Week:  {}", atStartOfWeek(now));
        log.info("At start of Month: {}", atStartOfMonth(now));
        log.info("At start of Year:  {}", atStartOfYear(now));
    }

    private static LocalDateTime atStartOfDay(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atStartOfDay();
    }

    private static LocalDateTime atStartOfWeek(LocalDateTime localDateTime) {
        return atStartOfDay(localDateTime.with(DayOfWeek.MONDAY));
    }

    private static LocalDateTime atStartOfMonth(LocalDateTime localDateTime) {
        return atStartOfDay(localDateTime.withDayOfMonth(1));
    }

    private static LocalDateTime atStartOfYear(LocalDateTime localDateTime) {
        return atStartOfDay(localDateTime.withDayOfYear(1));
    }
}
