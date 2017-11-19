package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class SkierQueueConsumer implements Runnable {

  private final BlockingQueue<IPair> skierQueue;
  private ConcurrentMap<String, Integer> skierNumRides;
  private ConcurrentMap<String, Integer> skierVerticalMeters;
  private static final String LIFT_LEVEL1 = "10";
  private static final String LIFT_LEVEL2 = "20";
  private static final String LIFT_LEVEL3 = "30";
  private static final int LIFT_LEVEL1_HEIGHT = 200;
  private static final int LIFT_LEVEL2_HEIGHT = 300;
  private static final int LIFT_LEVEL3_HEIGHT = 400;
  private static final int LIFT_LEVEL4_HEIGHT = 500;
  private static final IPair SKIER_SENTINEL = new Pair("", "");

  public SkierQueueConsumer(BlockingQueue<IPair> skierQueue,
                            ConcurrentMap<String, Integer> skierNumRides,
                            ConcurrentMap<String, Integer> skierVerticalMeters) {
    this.skierQueue = skierQueue;
    this.skierNumRides = skierNumRides;
    this.skierVerticalMeters = skierVerticalMeters;
  }

  @Override
  public void run() {
    try {
      while (true) {
        IPair pair = skierQueue.take();
        if (pair.equals(SKIER_SENTINEL)) {
          skierQueue.add(SKIER_SENTINEL);
          return;
        }
        consume(pair);
      }
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
  }

  private void consume(IPair pair) {
    String skier = pair.getFirst();
    String lift = pair.getLast();
    skierNumRides.putIfAbsent(skier, 0);
    skierNumRides.replace(skier, skierNumRides.get(skier) + 1);
    skierVerticalMeters.putIfAbsent(skier, 0);
    skierVerticalMeters.replace(skier, skierVerticalMeters.get(skier) + verticalOfLift(lift));
  }

  private int verticalOfLift(String lift) {
    if (lift.compareTo(LIFT_LEVEL1) <= 0) {
      return LIFT_LEVEL1_HEIGHT;
    } else if (lift.compareTo(LIFT_LEVEL2) <= 0) {
      return LIFT_LEVEL2_HEIGHT;
    } else if (lift.compareTo(LIFT_LEVEL3) <= 0) {
      return LIFT_LEVEL3_HEIGHT;
    } else {
      return LIFT_LEVEL4_HEIGHT;
    }
  }
}
