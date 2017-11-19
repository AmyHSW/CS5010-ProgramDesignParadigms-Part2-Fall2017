package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.Hour;
import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class HourQueueConsumer extends Consumer {

  private final List<ConcurrentMap<String, Integer>> hourRides;

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
    map.replace(lift, map.get(lift) + 1);
  }

}
