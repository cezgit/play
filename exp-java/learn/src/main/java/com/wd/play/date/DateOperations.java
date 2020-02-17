package com.wd.play.date;

import java.time.*;

public class DateOperations {

    /**
     * localDate        now date
     * utcDate          now date in UTC
     * millis           now millis
     * millisUTC        now millis in UTC
     * someDate         local date in the past
     * someUTC          UTC date in the past
     * someMillis       local instant in the past
     * someMillisUTC    UTC instant in the past
     *
     * localDate
     * millis
     * utcDate
     * millisUTC
     *
     * string to someDate
     * string to someUtc
     * string to someMillis
     * string to someMillisUTC
     *
     * someMillis to someDate
     * someMillis to someUTC
     *
     * someMillisUTC to someUTC
     * someMillisUTC to someDate
     *
     * someDate to someMillis
     * someDate to someUTC
     * someDate to someMillisUTC
     * someDate to string
     *
     * someUTC to someMillisUTC
     * someUTC to someDate
     * someUTC to someMillis
     *
     */

    public static void main(String[] args) {


        LocalDateTime fixedNow = LocalDateTime.now();
        System.out.println("fixedNow: "+fixedNow);

        Instant fixedInstantNow = Instant.now();
        System.out.println("fixedInstant: "+fixedInstantNow);
        Long fixedMillis = fixedInstantNow.toEpochMilli();
        System.out.println("fixedMillis: "+fixedMillis);

        ZonedDateTime ldtZoned = fixedNow.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime fixedNowUTC = utcZoned.toLocalDateTime();
        System.out.println("fixedNowUTC: "+fixedNowUTC);

        LocalDateTime nowUTC1 = LocalDateTime.now(Clock.systemUTC());
        System.out.println("nowUTC1: "+nowUTC1);
        LocalDateTime nowUTC2 = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println("nowUTC2: "+nowUTC2);

        LocalDateTime fixedDateTime = Instant.ofEpochMilli(fixedMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("fixedDateTime: "+fixedDateTime);

        LocalDateTime datetimeUTC = Instant.ofEpochMilli(fixedMillis).atZone(ZoneId.of("UTC")).toLocalDateTime();
        System.out.println("fixedDateTimeUTC: "+datetimeUTC);

        ZonedDateTime zdt = LocalDateTime.now().atZone(ZoneId.of("UTC"));
        System.out.println("zdt: "+zdt);

        Long epochMilliUTC = zdt.toInstant().toEpochMilli();
        System.out.println("zdt epochMilli: "+epochMilliUTC);

        Long nowMillis = System.currentTimeMillis();
        System.out.println("nowMillis: "+nowMillis);


        System.out.println("LocalTime.of(13,45,20): "+ LocalTime.of(13,45,20));

    }
}

