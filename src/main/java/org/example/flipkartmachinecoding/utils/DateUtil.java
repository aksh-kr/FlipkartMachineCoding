package org.example.flipkartmachinecoding.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String addDays(String date, int days) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        localDate = localDate.plusDays(days);
        return localDate.format(formatter);
    }
}
