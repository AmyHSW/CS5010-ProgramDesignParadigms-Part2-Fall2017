package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.consumers.HourQueueConsumer;
import edu.neu.ccs.cs5010.consumers.LiftQueueConsumer;
import edu.neu.ccs.cs5010.producers.Producer;
import edu.neu.ccs.cs5010.consumers.SkierQueueConsumer;
import edu.neu.ccs.cs5010.pairs.IPair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ConcurrentProcessor extends Processor {

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

  @Override
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
  public List<String> getSkierOutput() {
    List<ConcurrentMap.Entry<String, Integer>> entries = new ArrayList<>(skierVerticalMeters.entrySet());
    entries.sort((meters1, meters2) -> meters2.getValue().compareTo(meters1.getValue()));
    List<String> skierVerticalTotals = new ArrayList<>();
    skierVerticalTotals.add("SkierID,Vertical");
    for (int i = 0; i < 100; i++) {
      Map.Entry<String, Integer> verticalMeters = entries.get(i);
      String line = verticalMeters.getKey() + "," + verticalMeters.getValue();
      skierVerticalTotals.add(line);
    }
    return skierVerticalTotals;
  }

  @Override
  public List<String> getLiftOutput() {
    List<Map.Entry<String, Integer>> entries = new ArrayList<>(liftNumRides.entrySet());
    entries.sort(Comparator.comparing(Map.Entry::getKey));
    List<String> liftsRides = new ArrayList<>();
    liftsRides.add("LiftID, Number of Rides");
    for (Map.Entry<String, Integer> entry: entries) {
      String line = entry.getKey() + "," + entry.getValue();
      liftsRides.add(line);
    }
    return liftsRides;
  }

  @Override
  public List<String> getHourOutput() {
    List<ConcurrentMap.Entry<String, ConcurrentMap<String, Integer>>> entries = new ArrayList<>(hourRides.entrySet());
    entries.sort(Comparator.comparing(Map.Entry::getKey));
    List<String> hourLiftRides = new ArrayList<>();
    String header = "Hour,Number of Rides";
    for (ConcurrentMap.Entry<String, ConcurrentMap<String, Integer>> entry: entries) {
      List<Map.Entry<String, Integer>> liftsRides = new ArrayList<>(entry.getValue().entrySet());
      liftsRides.sort((rides1, rides2) -> rides2.getValue().compareTo(rides1.getValue()));
      hourLiftRides.add(header);
      for (int i = 0; i < 10; i++) {
        String line = entry.getKey() + "," + liftsRides.get(i).getValue();
        hourLiftRides.add(line);
      }
    }
    return hourLiftRides;
  }
}
