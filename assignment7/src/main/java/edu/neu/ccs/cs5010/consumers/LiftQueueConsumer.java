package edu.neu.ccs.cs5010.consumers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class LiftQueueConsumer extends Consumer {

  private final ConcurrentMap<String, Integer> liftNumRides;

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
    liftNumRides.replace(lift, liftNumRides.get(lift) + 1);
  }
}
