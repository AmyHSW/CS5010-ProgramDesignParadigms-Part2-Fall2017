package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.Lift;
import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * The SkierQueueConsumer represents a consumer for skier queue.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SkierQueueConsumer extends Consumer {

  private ConcurrentMap<String, Integer> skierNumRides;
  private ConcurrentMap<String, Integer> skierVerticalMeters;

  /**
   * The constructor of SkierQueueConsumer
   *
   * @param skierQueue BlockingQueue that stores all skiers info
   * @param skierNumRides ConcurrentMap that stores each skier's number of rides
   * @param skierVerticalMeters ConcurrentMap that stores each skier's vertical meters
   */
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
    skierNumRides.putIfAbsent(skier, 0);
    skierNumRides.replace(skier, skierNumRides.get(skier) + 1);
    skierVerticalMeters.putIfAbsent(skier, 0);
    String lift = pair.getLast();
    while (true) {
      if (skierVerticalMeters.replace(skier, skierVerticalMeters.get(skier),
              skierVerticalMeters.get(skier) + Lift.toVerticalMeters(lift))) {
        return;
      }
    }

  }

}
