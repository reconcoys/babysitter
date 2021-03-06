package com.pillar;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Babysitter {

    private static final long START_TO_BED_PAY = 12;
    private static final long BED_TO_MIDNIGHT_PAY = 8;
    private static final long MIDNIGHT_TO_END_PAY = 16;

    public long calculatePay(DateTime start, DateTime end, DateTime bedTime) throws Exception {

        validateInput(start, end, bedTime);

        DateTime midnight = (new DateTime(start.getYear(), start.getMonthOfYear(), start.getDayOfMonth(), 0, 0)).plusDays(1);
        Duration bedToMidnightDuration = new Duration(0);
        Duration midnightToEndDuration = new Duration(0);
        Duration beforeBedDuration = new Duration(start, end);

        if (end.isAfter(bedTime)) {
            beforeBedDuration = new Duration(start, bedTime);
            if (end.isAfter(midnight)) {
                bedToMidnightDuration = new Duration(bedTime, midnight);
                midnightToEndDuration = new Duration(midnight, end);
            }
            else {
                bedToMidnightDuration = new Duration(bedTime, end);
            }
        }

        long pay = beforeBedDuration.getStandardHours() * START_TO_BED_PAY;
        pay += bedToMidnightDuration.getStandardHours() * BED_TO_MIDNIGHT_PAY;
        pay += midnightToEndDuration.getStandardHours() * MIDNIGHT_TO_END_PAY;

        return pay;
    }

    private void validateInput(DateTime start, DateTime end, DateTime bedTime) throws Exception {
        if (start.getMinuteOfHour() != 0) {
            throw new Exception("Start must be at the start of an hour");
        }
        if (end.getMinuteOfHour() != 0) {
            throw new Exception("End must be at the start of an hour");
        }
        if (bedTime.getMinuteOfHour() != 0) {
            throw new Exception("Bedtime must be at the start of an hour");
        }
        if (start.getHourOfDay() < 17) {
            throw new Exception("Babysitter starts no earlier than 5pm");
        }
        if (end.getHourOfDay() > 4 && end.getDayOfMonth() > start.getDayOfMonth()) {
            throw new Exception("Babysitter leaves no later than 4am");
        }
        if (bedTime.getDayOfMonth() != start.getDayOfMonth()) {
            throw new Exception("Bedtime must be before midnight");
        }
    }
}
