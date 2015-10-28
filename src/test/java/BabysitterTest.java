import com.pillar.Babysitter;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BabysitterTest {

  /*- starts no earlier than 5:00PM
    - leaves no later than 4:00AM
    - gets paid $12/hour from start-time to bedtime
    - gets paid $8/hour from bedtime to midnight
    - gets paid $16/hour from midnight to end of job
    - gets paid for full hours (no fractional hours)*/

    private Babysitter babysitter = new Babysitter();

    @Test
    public void testBabysitterOneHourBeforeBedTime() throws Exception {
        DateTime start = new DateTime(2015, 10, 26, 17, 0);
        DateTime end = new DateTime(2015, 10, 26, 18, 0);
        DateTime bedTime = new DateTime(2015, 10, 26, 22, 0);

        long pay = babysitter.calculatePay(start, end, bedTime);
        assertEquals(12, pay);
    }

    @Test
    public void testBabysitterMultipleHoursBeforeBedTime() throws Exception {
        DateTime start = new DateTime(2015, 10, 26, 17, 0);
        DateTime end = new DateTime(2015, 10, 26, 22, 0);
        DateTime bedTime = new DateTime(2015, 10, 26, 22, 0);

        long pay = babysitter.calculatePay(start, end, bedTime);
        assertEquals(60, pay);
    }

    @Test
    public void testBabysitterMultipleHoursIncludingBedTimeNotPastMidnight() throws Exception {
        DateTime start = new DateTime(2015, 10, 26, 17, 0);
        DateTime end = new DateTime(2015, 10, 26, 23, 0);
        DateTime bedTime = new DateTime(2015, 10, 26, 21, 0);

        long pay = babysitter.calculatePay(start, end, bedTime);
        assertEquals(64, pay);
    }

    @Test
    public void testBabysitterMultipleHoursPastMidnight() throws Exception {
        DateTime start = new DateTime(2015, 10, 26, 17, 0);
        DateTime end = new DateTime(2015, 10, 27, 2, 0);
        DateTime bedTime = new DateTime(2015, 10, 26, 21, 0);

        long pay = babysitter.calculatePay(start, end, bedTime);
        assertEquals(104, pay);
    }

    @Test
    public void testBabysitterStartBefore5PM(){
        DateTime start = new DateTime(2015, 10, 26, 16, 0);
        DateTime end = new DateTime(2015, 10, 27, 2, 0);
        DateTime bedTime = new DateTime(2015, 10, 26, 21, 0);

        String exceptionMessage = "";
        try {
            babysitter.calculatePay(start, end, bedTime);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Babysitter starts no earlier than 5pm", exceptionMessage);
    }

    @Test
    public void testBabysitterLeaveAfter4AM(){
        DateTime start = new DateTime(2015, 10, 26, 17, 0);
        DateTime end = new DateTime(2015, 10, 27, 5, 0);
        DateTime bedTime = new DateTime(2015, 10, 26, 21, 0);

        String exceptionMessage = "";
        try {
            babysitter.calculatePay(start, end, bedTime);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Babysitter leaves no later than 4am", exceptionMessage);
    }

    @Test
    public void testBabysitterBedTimeAfterMidnight(){
        DateTime start = new DateTime(2015, 10, 26, 17, 0);
        DateTime end = new DateTime(2015, 10, 27, 2, 0);
        DateTime bedTime = new DateTime(2015, 10, 27, 1, 0);

        String exceptionMessage = "";
        try {
            babysitter.calculatePay(start, end, bedTime);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Bedtime must be before midnight", exceptionMessage);
    }

    @Test
    public void testBabysitterStartFractionalHour(){
        DateTime start = new DateTime(2015, 10, 26, 17, 30);
        DateTime end = new DateTime(2015, 10, 27, 2, 0);
        DateTime bedTime = new DateTime(2015, 10, 26, 23, 0);

        String exceptionMessage = "";
        try {
            babysitter.calculatePay(start, end, bedTime);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Start must be at the start of an hour", exceptionMessage);
    }

    @Test
    public void testBabysitterEndFractionalHour(){
        DateTime start = new DateTime(2015, 10, 26, 17, 00);
        DateTime end = new DateTime(2015, 10, 27, 2, 30);
        DateTime bedTime = new DateTime(2015, 10, 26, 23, 0);

        String exceptionMessage = "";
        try {
            babysitter.calculatePay(start, end, bedTime);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("End must be at the start of an hour", exceptionMessage);
    }

    @Test
    public void testBabysitterBedTimeFractionalHour(){
        DateTime start = new DateTime(2015, 10, 26, 17, 00);
        DateTime end = new DateTime(2015, 10, 27, 2, 00);
        DateTime bedTime = new DateTime(2015, 10, 26, 23, 20);

        String exceptionMessage = "";
        try {
            babysitter.calculatePay(start, end, bedTime);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Bedtime must be at the start of an hour", exceptionMessage);
    }
}
