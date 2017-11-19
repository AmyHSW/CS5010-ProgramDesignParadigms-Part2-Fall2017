package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.LiftToHeightConverter;
import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class SkierQueueConsumer extends Consumer {

  private ConcurrentMap<String, Integer> skierNumRides;
  private ConcurrentMap<String, Integer> skierVerticalMeters;

  public SkierQueueConsumer(BlockingQueue<IPair> skierQueue,
                            ConcurrentMap<String, Integer> skierNumRides,
                            ConcurrentMap<String, Integer> skierVerticalMeters) {
    this.queue = skierQueue;
    this.skierNumRides = skierNumRides;
    this.skierVerticalMeters = skierVerticalMeters;
    this.sentinel = new Pair("", "");
  }

  @Override
  public void consume() {
    IPair pair = (IPair) item;
    String skier = pair.getFirst();
    String lift = pair.getLast();
    skierNumRides.putIfAbsent(skier, 0);
    skierNumRides.replace(skier, skierNumRides.get(skier) + 1);
    skierVerticalMeters.putIfAbsent(skier, 0);
    skierVerticalMeters.replace(skier,
        skierVerticalMeters.get(skier) + LiftToHeightConverter.toHeight(lift));
  }

}
