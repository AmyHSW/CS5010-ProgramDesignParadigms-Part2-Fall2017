package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.consumers.HourQueueConsumer;
import edu.neu.ccs.cs5010.consumers.LiftQueueConsumer;
import edu.neu.ccs.cs5010.producers.Producer;
import edu.neu.ccs.cs5010.consumers.SkierQueueConsumer;
import edu.neu.ccs.cs5010.pairs.IPair;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ConcurrentProcessor implements IProcessor {

  private static final int NUM_THREADS = 1;
  private static final int NUM_SECONDS_WAIT = 5;

  private final List<String[]> inputData;
  private final BlockingQueue<IPair> skierQueue;
  private final BlockingQueue<String> liftQueue;
  private final BlockingQueue<IPair> hourQueue;
  private final ConcurrentMap<String, Integer> skierNumRides;
  private final ConcurrentMap<String, Integer> skierVerticalMeters;
  private final ConcurrentMap<String, Integer> liftNumRides;
  private final ConcurrentMap<String, ConcurrentMap<String, Integer>> hourRides;

  private final ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS * 3);

  public ConcurrentProcessor(List<String[]> inputData) {
    this.inputData = inputData.subList(1, inputData.size());
    skierQueue = new LinkedBlockingDeque<>();
    liftQueue = new LinkedBlockingDeque<>();
    hourQueue = new LinkedBlockingDeque<>();
    skierNumRides = new ConcurrentHashMap<>();
    skierVerticalMeters = new ConcurrentHashMap<>();
    liftNumRides = new ConcurrentHashMap<>();
    hourRides = new ConcurrentHashMap<>();
  }

  public void processInput() throws InterruptedException {
    long startTime = System.currentTimeMillis();

    Producer producer = new Producer(inputData, skierQueue, liftQueue, hourQueue);
    new Thread(producer).start();

    for (int i = 0; i < NUM_THREADS; i++) {
      SkierQueueConsumer skierQueueConsumer =
          new SkierQueueConsumer(skierQueue, skierNumRides, skierVerticalMeters);
      LiftQueueConsumer liftQueueConsumer = new LiftQueueConsumer(liftQueue, liftNumRides);
      HourQueueConsumer hourQueueConsumer = new HourQueueConsumer(hourQueue, hourRides);
      executorService.execute(skierQueueConsumer);
      executorService.execute(liftQueueConsumer);
      executorService.execute(hourQueueConsumer);
    }
    executorService.shutdown();
    executorService.awaitTermination(NUM_SECONDS_WAIT, TimeUnit.SECONDS);

    System.out.println("concurrent runs " + (System.currentTimeMillis() - startTime));
  }

  @Override
  public Map<String, Integer> getSkierVerticalMeters() {
    return skierVerticalMeters;
  }

  @Override
  public Map<String, Integer> getLiftNumRides() {
    return liftNumRides;
  }

  @Override
  public Map<String, Map<String, Integer>> getHourRides() {
    Map<String, Map<String, Integer>> temp = new HashMap<>();
    temp.putAll(hourRides);
    return temp;
  }

}
