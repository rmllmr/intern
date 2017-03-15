package mobi.morethreadscach;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 15.03.2017.
 */
public class morethrreadscacheTest extends morethrreadscache {
    @Override
    public void run(){
        this.put("firstKeyCache", "firstValueCache", 3000);
        this.put("secondKeyCache", "secondValueCache", 3000);
        this.get("firstKeyCache");
        this.get("secondKeyCache");
    }

    @Test
    public void get() throws Exception {

        Thread firstThread = new Thread(new morethrreadscache<String>());
        Thread secondThread = new Thread(new morethrreadscache<String>());
        firstThread.start();
        secondThread.start();
    }

    @Test
    public void put() throws Exception {

        Thread firstThread = new Thread(new morethrreadscache<String>());
        Thread secondThread = new Thread(new morethrreadscache<String>());
        firstThread.start();
        secondThread.start();
    }

}