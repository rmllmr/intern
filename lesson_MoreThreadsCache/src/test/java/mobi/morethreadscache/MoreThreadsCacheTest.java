package mobi.morethreadscache;

import org.junit.Test;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import java.util.Random;


/**
 * Created by user on 15.03.2017.
 */
public class MoreThreadsCacheTest {
    @Test
    public void get() throws Exception {

        MoreThreadsCache<Integer> testCache = new MoreThreadsCache<>();
        Thread getThreadGet = new Thread(new MoreThreadsCacheTestGet(testCache));
        Thread getThreadSet = new Thread(new MoreThreadsCacheTestSet(testCache));

        getThreadSet.start();
        getThreadGet.start();
    }

    @Test
    public void put() throws Exception {
        MoreThreadsCache<Integer> testCache = new MoreThreadsCache<>();
        Thread getThreadGet = new Thread(new MoreThreadsCacheTestGet(testCache));
        Thread getThreadSet = new Thread(new MoreThreadsCacheTestSet(testCache));

        getThreadSet.start();
        sleep(50);
        getThreadGet.start();
    }

}


class MoreThreadsCacheTestGet implements Runnable {

    private MoreThreadsCache<Integer> testCache;

    public MoreThreadsCacheTestGet(MoreThreadsCache testCache){
        this.testCache = testCache;
    }

    @Override
    public void run(){
        for (Integer i = 0; i < 10; i++) {

            assertEquals(" EL " +i.toString() + " = ", i, testCache.get(i.toString()));

        }
    }

}

class MoreThreadsCacheTestSet implements Runnable {

    private MoreThreadsCache<Integer> testCache;

    public MoreThreadsCacheTestSet(MoreThreadsCache testCache){
        this.testCache = testCache;
    }

    private Integer randomValue(){
        Integer value = new Random().nextInt(50);
        return value;
    }

    @Override
    public void run() {

        for (Integer i = 0; i < 10; i++) {
            testCache.put(i.toString(), i, 5000000);
        }

    }
}