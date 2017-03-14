package mobi.factorial;

/**
 * Created by LuMoR on 13.03.2017.
 */

import java.math.BigInteger;
import java.util.concurrent.Callable;

/**
 * Created by user on 13.03.2017.
 */
public class FactorialUtil implements Callable<BigInteger> {
    private int start;
    private int end;
    private BigInteger result;

    public FactorialUtil(int startValueFactorialToThreads, int endValueFactorialToThreads, BigInteger result){
        this.start = startValueFactorialToThreads;
        this.end = endValueFactorialToThreads;
        this.result = result;
    }

    public BigInteger call() throws Exception {

        BigInteger resultFactorialToThreads = result;

        for (int i = start; i <= end; ++i) {
            resultFactorialToThreads = resultFactorialToThreads.multiply(BigInteger.valueOf(i));
        }
        return resultFactorialToThreads;

    }
}
