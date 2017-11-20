package edu.neu.ccs.cs5010.consumers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * The LiftQueueConsumer represents a consumer for lift queue.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class LiftQueueConsumer extends Consumer {

  private final ConcurrentMap<String, Integer> liftNumRides;

  /**
   * The constructor of LiftQueueConsumer
   *
   * @param liftQueue BlockingQueue that stores all lifts info
   * @param liftNumRides ConcurrentMap that stores each lift's number of rides
   */
  public LiftQueueConsumer(BlockingQueue<String> liftQueue,
                           ConcurrentMap<String, Integer> liftNumRides) {
    this.queue = liftQueue;
    this.liftNumRides = liftNumRides;
    this.sentinel = "";
  }

  @Override
  public void consume() {
    String lift = (String) item;
    liftNumRides.putIfAbsent(lift, 0);
    while (true) {
      if (liftNumRides.replace(lift, liftNumRides.get(lift), liftNumRides.get(lift) + 1)) {
        return;
      }
    }
  }
}
