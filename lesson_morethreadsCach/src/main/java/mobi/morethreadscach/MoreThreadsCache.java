package mobi.morethreadscach;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 14.03.2017.
 */
public class MoreThreadsCache<T> implements Runnable{

    private Map<String, T> cacheValue = new HashMap<String, T>();
    private Map<String, Long> cacheTimeLive = new HashMap<String, Long>();
    private Lock lock = new ReentrantLock();;

    public T get(String keyCache){
        checkRCache();
        try {
            lock.lock();
                if (cacheTimeLive.containsKey(keyCache)){
                    return cacheValue.get(keyCache);
                }

        } finally{
            lock.unlock();
        }

        return null;
    };


    public void put(String keyCache, T valueCashe, int timeLive){
        checkRCache();
        try {
            lock.lock();
                if (timeLive > 0) {
                    long timeLiveToCache = timeLive * 1000 + System.currentTimeMillis();
                    cacheValue.put(keyCache, valueCashe);
                    cacheTimeLive.put(keyCache, timeLiveToCache);
                }

        } finally{
            lock.unlock();
        }

    }

    private void checkRCache(){
        long timeNow = System.currentTimeMillis();
        for (String elementCache: cacheTimeLive.keySet()) {
            try {
                lock.lock();
                    if (cacheTimeLive.get(elementCache) < timeNow){
                        cacheTimeLive.remove(elementCache);
                        cacheValue.remove(elementCache);
                    }
            } finally{
                lock.unlock();
            }

        }

    }

    @Override
    public void run() {

    }
}

