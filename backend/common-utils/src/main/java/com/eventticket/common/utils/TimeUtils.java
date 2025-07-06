package com.eventticket.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeUtils {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static boolean isDateInFuture(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }
    
    public static boolean isDateTimeInFuture(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }
    
    public static boolean isDateInPast(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }
    
    public static boolean isDateTimeInPast(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }
    
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    public static long hoursBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.HOURS.between(startDateTime, endDateTime);
    }
    
    public static LocalDateTime getStartOfDay(LocalDate date) {
        return date.atStartOfDay();
    }
    
    public static LocalDateTime getEndOfDay(LocalDate date) {
        return date.atTime(LocalTime.MAX);
    }
    
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : null;
    }
    
    public static String formatTime(LocalTime time) {
        return time != null ? time.format(TIME_FORMATTER) : null;
    }
    
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATETIME_FORMATTER) : null;
    }
    
    public static LocalDate parseDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, DATE_FORMATTER) : null;
    }
    
    public static LocalDateTime parseDateTime(String dateTimeString) {
        return dateTimeString != null ? LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER) : null;
    }
    
    public static boolean isAgeValid(LocalDate birthDate, int minimumAge) {
        if (birthDate == null) return false;
        LocalDate today = LocalDate.now();
        return ChronoUnit.YEARS.between(birthDate, today) >= minimumAge;
    }
    
    public static boolean isEventTimeValid(LocalDateTime eventDate, LocalDateTime doorsOpenTime) {
        if (eventDate == null) return false;
        if (doorsOpenTime == null) return true;
        return doorsOpenTime.isBefore(eventDate);
    }
    
    public static boolean isSaleTimeValid(LocalDateTime saleStart, LocalDateTime saleEnd, LocalDateTime eventDate) {
        if (eventDate == null) return false;
        if (saleStart != null && saleStart.isAfter(eventDate)) return false;
        if (saleEnd != null && saleEnd.isAfter(eventDate)) return false;
        if (saleStart != null && saleEnd != null && saleStart.isAfter(saleEnd)) return false;
        return true;
    }
} 