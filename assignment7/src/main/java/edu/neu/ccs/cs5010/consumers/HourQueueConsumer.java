package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.Hour;
import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * The HourQueueConsumer represents a consumer for hour queue.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class HourQueueConsumer extends Consumer {

  private final List<ConcurrentMap<String, Integer>> hourRides;

  /**
   * The constructor of HourQueueConsumer
   *
   * @param hourQueue BlockingQueue that stores all hours info
   * @param hourRides ConcurrentMap that stores each hour's each lift's number of rides
   */
  public HourQueueConsumer(BlockingQueue<IPair> hourQueue,
                          List<ConcurrentMap<String, Integer>> hourRides) {
    this.queue = hourQueue;
    this.hourRides = hourRides;
    this.sentinel = new Pair("", "");
  }

  @Override
  public void consume() {
    IPair pair = (IPair) item;
    int hourIndex = Hour.toIndex(pair.getFirst());
    String lift = pair.getLast();
    ConcurrentMap<String, Integer> map = hourRides.get(hourIndex);
    map.putIfAbsent(lift, 0);
    while (true) {
      if (map.replace(lift, map.get(lift),map.get(lift) + 1)) {
        return;
      }
    }

  }

}
