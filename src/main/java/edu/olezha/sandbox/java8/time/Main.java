package edu.olezha.sandbox.java8.time;

import edu.olezha.sandbox.string.Str;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        /*
         * @See https://docs.oracle.com/javase/tutorial/datetime/iso/overview.html
         */

        parsingRFC1123DateTime();

        LocalDateTime localDateTime = LocalDateTime.of(2018, 3, 6, 23, 36);
        System.out.println(localDateTime);

        LocalDate localDate = LocalDate.from(localDateTime);
        System.out.println(localDate);

        LocalDate localDate2 = localDate.plusMonths(1);
        System.out.println(localDate2 + " " + localDate);

        int increment = 27;
        for (int year = 1700; year < 2700; year += increment) {
            if (year > 1918) {
                LocalDate localDate3 = LocalDate.ofYearDay(year, 256);
                System.out.println(localDate3.format(DateTimeFormatter.ofPattern("dd.LL.yyyy")));
                increment = 101;
            } else {
                System.out.println(getDayOfTheProgrammerDate(year));
            }
        }
    }

    private static void parsingRFC1123DateTime() {
        String dateTimeString = "Fri, 18 Dec 2020 13:05:04 GMT";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.RFC_1123_DATE_TIME);
        System.out.println(dateTime);
    }

    private static String getDayOfTheProgrammerDate(int year) {
        String jc = "Julian calendar: ";
        if (year == 1918)
            return jc + "26.09.1918";
        else if (((year <= 1917) && (year % 4 == 0)) ||
                ((year > 1918) && (year % 400 == 0 || ((year % 4 == 0) & (year % 100 != 0)))))
            return jc + "12.09." + year;
        else
            return jc + "13.09." + year;
    }
}
