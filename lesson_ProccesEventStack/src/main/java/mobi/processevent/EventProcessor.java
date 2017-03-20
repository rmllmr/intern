package mobi.processevent;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 16.03.2017.
 */



public class EventProcessor{

  private final int maxQueueSize;
  private LinkedList<Event> queue = new LinkedList<>();
  private Semaphore semaphore;
  private Lock lock = new ReentrantLock();
  private Lock lockQueueMonitor = new ReentrantLock();
  private Thread thread;

  private Runnable queueMonitor = new Runnable() {
    Event event;
    @Override
    public void run() {
      lockQueueMonitor.lock();
      while(true){
        boolean locked;
        if (semaphore.availablePermits() == maxQueueSize) {
          try {
             lockQueueMonitor.lock();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        lock.lock();
        event = queue.poll();
        lock.unlock();
        event.process();
        semaphore.release();
      }
    }
  };

  public EventProcessor(int maxQueueSize) {
    this.maxQueueSize = maxQueueSize;
    semaphore = new Semaphore(this.maxQueueSize);
    thread = new Thread(queueMonitor);
    thread.run();
  }

  public void process(Event event){

    try {
      semaphore.acquire();
      lock.lock();
      queue.offerFirst(event);
      lock.unlock();
      lockQueueMonitor.unlock();

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

