package mobi.morethreadscache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 14.03.2017.
 */
public class MoreThreadsCache<T> {

    private Map<String, T> cacheValue = new HashMap<String, T>();
    private Map<String, Long> cacheTimeLive = new HashMap<String, Long>();
    private Lock lock = new ReentrantLock();;

    public T get(String key){

            lock.lock();
                checkCache();
                if (cacheTimeLive.containsKey(key)){
                    return cacheValue.get(key);
                }
            lock.unlock();

        return null;
    };


    public void put(String key, T value, int timeToLive){

            lock.lock();
                checkCache();
                if (timeToLive > 0) {
                    long timeLiveToCache = timeToLive * 1000 + System.currentTimeMillis();
                    cacheValue.put(key, value);
                    cacheTimeLive.put(key, timeLiveToCache);
                }
            lock.unlock();


    }

    private void checkCache(){
        long timeNow = System.currentTimeMillis();
        for (String elementCache: cacheTimeLive.keySet()) {
            if (cacheTimeLive.get(elementCache) < timeNow){
                cacheTimeLive.remove(elementCache);
                cacheValue.remove(elementCache);
            }
        }

    }

}

