package com.pillar;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Babysitter {

    public long calculatePay(DateTime start, DateTime end, DateTime bedTime) {
        Duration duration = new Duration(start, end);
        return duration.getStandardHours() * 12;
    }
}
