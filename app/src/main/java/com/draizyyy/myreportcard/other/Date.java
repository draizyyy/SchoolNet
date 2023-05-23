package com.draizyyy.myreportcard.other;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Date {
    public static String getDate() {
        String dateString = LocalDateTime.now().toString().substring(0, 10);

        return "20 февраля";
//        return normalizeDate(dateString);
    }
    public static String getDate(int differenceOfDays) {
        String dateString = LocalDateTime.now().toString().substring(0, 10);
        int dayNumber =  ((Integer.parseInt(dateString.substring(8, 10)))) + differenceOfDays;
        String dayNum;
        if (dayNumber < 10) {
            dayNum = "0" + dayNumber;
        }
        else {
            dayNum = Integer.toString(dayNumber);
        }
        dateString = dateString.substring(0, 8) + dayNum;

        return "20 февраля";
//        return normalizeDate(dateString);
    }
    public static int getTodayDateNumber() {
        return LocalDate.now().getDayOfWeek().getValue() - 1;
    }

    public static String normalizeDate(String some_date) {
        LocalDate date = LocalDate.parse(some_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dateOfDay = date.format(DateTimeFormatter.ofPattern("dd MMMM", new Locale("ru")));

        if (dateOfDay.charAt(0) == '0') {
            dateOfDay = dateOfDay.substring(1);
        }

        return dateOfDay;
    }
}
