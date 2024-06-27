package com.ea.lab4part1.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static LocalDateTime dateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(date, formatter).atStartOfDay();
    }
}
