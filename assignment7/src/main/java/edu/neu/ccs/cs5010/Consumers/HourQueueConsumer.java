package edu.neu.ccs.cs5010.Consumers;

import edu.neu.ccs.cs5010.pairs.HourLiftIdPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HourQueueConsumer implements Runnable {

  private static final Pair HOUR_SENTINEL = new HourLiftIdPair("", "");
  private final BlockingQueue<Pair> hourQueue;
  private final ConcurrentMap<String, ConcurrentMap<String, Integer>> hourRides;

  public HourQueueConsumer(BlockingQueue<Pair> hourQueue,
                           ConcurrentMap<String, ConcurrentMap<String, Integer>> hourRides) {
    this.hourQueue = hourQueue;
    this.hourRides = hourRides;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Pair pair = hourQueue.take();
        if (pair.equals(HOUR_SENTINEL)) {
          hourQueue.add(HOUR_SENTINEL);
          return;
        }
        consume(pair);
      }
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
  }

  private void consume(Pair pair) {
    String hour = pair.getFirst();
    String lift = pair.getLast();
    hourRides.putIfAbsent(hour, new ConcurrentHashMap<>());
    ConcurrentMap<String, Integer> map = hourRides.get(hour);
    map.putIfAbsent(lift, 0);
    map.replace(lift, map.get(lift) + 1);
    hourRides.replace(hour, map);
  }

}
