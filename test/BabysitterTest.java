import com.pillar.Babysitter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BabysitterTest {

    @Test
    public void testBabysitter(){
        Babysitter babysitter = new Babysitter();
        int pay = babysitter.calculatePay(17, 18, 22);
        assertEquals(12, pay);
    }

}
