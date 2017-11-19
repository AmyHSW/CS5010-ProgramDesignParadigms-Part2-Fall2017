package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HourQueueConsumer extends Consumer {

  private final ConcurrentMap<String, ConcurrentMap<String, Integer>> hourRides;

  public HourQueueConsumer(BlockingQueue<IPair> hourQueue,
                           ConcurrentMap<String, ConcurrentMap<String, Integer>> hourRides) {
    this.queue = hourQueue;
    this.hourRides = hourRides;
    this.sentinel = new Pair("", "");
  }

  @Override
  public void consume() {
    IPair pair = (IPair) item;
    String hour = pair.getFirst();
    String lift = pair.getLast();
    hourRides.putIfAbsent(hour, new ConcurrentHashMap<>());
    ConcurrentMap<String, Integer> map = hourRides.get(hour);
    map.putIfAbsent(lift, 0);
    map.replace(lift, map.get(lift) + 1);
    hourRides.replace(hour, map);
  }

}
