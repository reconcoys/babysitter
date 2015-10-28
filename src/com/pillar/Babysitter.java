package com.pillar;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Babysitter {

    public long calculatePay(DateTime start, DateTime end, DateTime bedTime) throws Exception {

        if (start.getHourOfDay() < 17) {
            throw new Exception("Babysitter starts no earlier than 5pm");
        }

        DateTime midnight = (new DateTime(start.getYear(), start.getMonthOfYear(), start.getDayOfMonth(), 0, 0)).plusDays(1);
        Duration beforeBedDuration = end.isAfter(bedTime) ? new Duration(start, bedTime) : new Duration(start, end);
        Duration bedToMidnightDuration = new Duration(0);
        Duration midnightToEndDuration = new Duration(0);

        if (end.isAfter(bedTime)) {
            if (end.isAfter(midnight)) {
                bedToMidnightDuration = new Duration(bedTime, midnight);
            }
            else {
                bedToMidnightDuration = new Duration(bedTime, end);
            }
        }
        if (end.isAfter(midnight)) {
            midnightToEndDuration = new Duration(midnight, end);
        }
        long pay = beforeBedDuration.getStandardHours() * 12;
        pay += bedToMidnightDuration.getStandardHours() * 8;
        pay += midnightToEndDuration.getStandardHours() * 16;

        return pay;
    }
}
