package edu.olezha.sandbox.core.date.calendar;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        log.info("{}", calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        log.info("{}", calendar.getTime());
        log.info("{}", calendar.get(Calendar.YEAR));
        log.info("{}", calendar);
    }
}
