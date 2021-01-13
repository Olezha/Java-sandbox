package edu.olezha.sandbox.core.date.utilDate;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class Main {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Date date = new Date();
        log.info("My Now: {}", date);

        Date date0 = new Date(0);
        log.info("My Zero time: {}", date0);

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        log.info("UTC Now: {}", date);
        log.info("UTC Zero time: {}" + System.lineSeparator(), date0);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Kiev"));
        Date myBirthday = new Date(83, Calendar.AUGUST, 20);
        log.info("I was born in {}", myBirthday);
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
        log.info("I was born in {}", myBirthday);
    }
}
