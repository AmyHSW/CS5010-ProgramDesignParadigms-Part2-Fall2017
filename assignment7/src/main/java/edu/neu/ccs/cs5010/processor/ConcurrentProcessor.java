package edu.neu.ccs.cs5010.processor;

import edu.neu.ccs.cs5010.pairs.HourLiftIdPair;
import edu.neu.ccs.cs5010.pairs.Pair;
import edu.neu.ccs.cs5010.pairs.SkierLiftIdPair;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class ConcurrentProcessor implements Processor {

  private final BlockingQueue<Pair> skierQueue = new LinkedBlockingDeque<>();
  private final BlockingQueue<String> liftQueue = new LinkedBlockingDeque<>();
  private final BlockingQueue<Pair> hourQueue = new LinkedBlockingDeque<>();

  private final ConcurrentMap<String, Integer> skierNumRides = new ConcurrentHashMap<>();
  private final ConcurrentMap<String, Integer> skierVerticalMeters = new ConcurrentHashMap<>();
  private final ConcurrentMap<String, Integer> liftNumRides = new ConcurrentHashMap<>();
  private final ConcurrentMap<Integer, Map<String, Integer>> hourRides = new ConcurrentHashMap<>();

  private final ExecutorService executorService = Executors.newFixedThreadPool(10);;

  public ConcurrentProcessor(List<String[]> inputData) {
    setupQueues(inputData.subList(1, inputData.size()));
  }

  private void setupQueues(List<String[]> inputData) {
    for (String[] record : inputData) {
      skierQueue.offer(new SkierLiftIdPair(record[2], record[3]));
      liftQueue.offer(record[3]);
      int hour = Integer.parseInt(record[4]) / 60 + 1;
      hourQueue.offer(new HourLiftIdPair(Integer.toString(hour), record[3]));
    }
  }

  @Override
  public void processInput() {
    while (!skierQueue.isEmpty()) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          if (!skierQueue.isEmpty()) {
            process(skierQueue.poll());
          }
        }
      });
    }
    executorService.shutdown();
  }

  private void process(Pair pair) {
    if (pair == null) {
      System.out.println("pair is null");
    }
    String skier = pair.getFirst();
    String lift = pair.getLast();
    skierNumRides.put(skier, skierNumRides.getOrDefault(skier, 0) + 1);
    if (lift.compareTo("10") <= 0) {
      skierVerticalMeters.put(skier, skierVerticalMeters.getOrDefault(skier, 0) + 200);
    } else if (lift.compareTo("20") <= 0) {
      skierVerticalMeters.put(skier, skierVerticalMeters.getOrDefault(skier, 0) + 300);
    } else if (lift.compareTo("30") <= 0) {
      skierVerticalMeters.put(skier, skierVerticalMeters.getOrDefault(skier, 0) + 400);
    } else {
      skierVerticalMeters.put(skier, skierVerticalMeters.getOrDefault(skier, 0) + 500);
    }
  }

}
