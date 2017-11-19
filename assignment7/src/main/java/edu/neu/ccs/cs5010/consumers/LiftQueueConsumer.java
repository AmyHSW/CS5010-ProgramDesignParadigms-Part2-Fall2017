package edu.neu.ccs.cs5010.consumers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class LiftQueueConsumer implements Runnable {

  private static final String LIFT_SENTINEL = "";
  private final BlockingQueue<String> liftQueue;
  private final ConcurrentMap<String, Integer> liftNumRides;

  public LiftQueueConsumer(BlockingQueue<String> liftQueue,
                           ConcurrentMap<String, Integer> liftNumRides) {
    this.liftQueue = liftQueue;
    this.liftNumRides = liftNumRides;
  }

  @Override
  public void run() {
    try {
      while (true) {
        String lift = liftQueue.take();
        if (lift.equals(LIFT_SENTINEL)) {
          liftQueue.add(LIFT_SENTINEL);
          return;
        }
        consume(lift);
      }
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
  }

  private void consume(String lift) {
    liftNumRides.putIfAbsent(lift, 0);
    liftNumRides.replace(lift, liftNumRides.get(lift) + 1);
  }
}
