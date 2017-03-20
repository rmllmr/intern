package mobi.processevent;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by LuMoR on 19.03.2017.
 */
public class EventProcessorTest {
    @Test
    public void process() throws Exception {
        EventProcessor eventProcessor  = new EventProcessor(5);
//        TestEvent testEvent = new TestEvent(3,5);
//        eventProcessor.process(testEvent);
//        assertEquals( 8, testEvent.getSumAB());
    }


}

class TestEvent implements Event{
    private int testA = 0;
    private int testB = 0;
    private int sumAB= 0;
    public TestEvent(int testA, int testB) {
        this.testA = testA;
        this.testB = testB;
    }

    @Override
    public void process() {
        this.sumAB = this.testA + this.testB;
    }

    public int getSumAB() {
        return this.sumAB;
    }
}