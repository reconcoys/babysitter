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

    @Test
    public void testBabysitterOneHourBeforeBedTime(){
        DateTime start = new DateTime(2015, 10, 26, 17, 0);
        DateTime end = new DateTime(2015, 10, 26, 18, 0);
        DateTime bedTime = new DateTime(2015, 10, 26, 22, 0);

        Babysitter babysitter = new Babysitter();
        int pay = babysitter.calculatePay(start, end, bedTime);
        assertEquals(12, pay);
    }

}