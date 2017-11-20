package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.lift.Lift;
import edu.neu.ccs.cs5010.skier.ISkier;
import edu.neu.ccs.cs5010.skier.Skier;
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

  private ConcurrentMap<String, ISkier> skierMap;

  /**
   * The constructor of SkierQueueConsumer
   *
   * @param skierQueue BlockingQueue that stores all skiers info
   */
  public SkierQueueConsumer(BlockingQueue<IPair> skierQueue,
                            ConcurrentMap<String, ISkier> skierMap) {
    this.queue = skierQueue;
    this.skierMap = skierMap;
    this.sentinel = new Pair("", "");
  }

  @Override
  public void consume() {
    IPair pair = (IPair) item;
    String skierId = pair.getFirst();
    String liftId = pair.getLast();
    skierMap.putIfAbsent(skierId, new Skier(skierId));
    skierMap.get(skierId).incrementNumRides();
    skierMap.get(skierId).increaseVerticalMeters(Lift.toVerticalMeters(liftId));
  }

}
