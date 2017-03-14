package mobi.morethreadscach;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by user on 14.03.2017.
 */
public class morethrreadscach<T>{

    private Map<String, T> cacheValue = new HashMap<String, T>();
    private Map<String, Long> cacheTimeLive = new HashMap<String, Long>();
    private Lock lock;


    public T get(String keyRCache){
        checkRCache();
        try {
            if(lock.tryLock()){
                if (cacheTimeLive.containsKey(keyRCache)){
                    return cacheValue.get(keyRCache);
                }
            }
        } finally{
            lock.unlock();
        }

        return null;
    };


    public void put(String keyRCache, T valueRCashe, int timeLive){
        checkRCache();
        try {
            if(lock.tryLock()){
                if (timeLive > 0) {
                    long timeLiveToCache = timeLive * 1000 + System.currentTimeMillis();
                    cacheValue.put(keyRCache, valueRCashe);
                    cacheTimeLive.put(keyRCache, timeLiveToCache);
                }
            }
        } finally{
            lock.unlock();
        }

    }

    private void checkRCache(){
        long timeNow = System.currentTimeMillis();
        for (String rElementCache: cacheTimeLive.keySet()) {
            try {
                if(lock.tryLock()){
                    if (cacheTimeLive.get(rElementCache) < timeNow){
                        cacheTimeLive.remove(rElementCache);
                        cacheValue.remove(rElementCache);
                    }
                }
            } finally{
                lock.unlock();
            }

        }

    }
}

