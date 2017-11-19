package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.Hour;
import edu.neu.ccs.cs5010.consumers.HourQueueConsumer;
import edu.neu.ccs.cs5010.consumers.LiftQueueConsumer;
import edu.neu.ccs.cs5010.exceptions.InvalidInputDataException;
import edu.neu.ccs.cs5010.producers.Producer;
import edu.neu.ccs.cs5010.consumers.SkierQueueConsumer;
import edu.neu.ccs.cs5010.pairs.IPair;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

  private final ExecutorService executorService = Executors.newCachedThreadPool();

  private final List<String[]> inputData;
  private final BlockingQueue<IPair> skierQueue;
  private final BlockingQueue<String> liftQueue;
  private final BlockingQueue<IPair> hourQueue;
  private final ConcurrentMap<String, Integer> skierNumRides;
  private final ConcurrentMap<String, Integer> skierVerticalMeters;
  private final ConcurrentMap<String, Integer> liftNumRides;
  private final List<ConcurrentMap<String, Integer>> hourRides;

  private Duration runTime;

  public ConcurrentProcessor(List<String[]> inputData) {
    validate(inputData);

    this.inputData = inputData.subList(1, inputData.size());
    skierQueue = new LinkedBlockingDeque<>();
    liftQueue = new LinkedBlockingDeque<>();
    hourQueue = new LinkedBlockingDeque<>();
    skierNumRides = new ConcurrentHashMap<>();
    skierVerticalMeters = new ConcurrentHashMap<>();
    liftNumRides = new ConcurrentHashMap<>();
    hourRides = new ArrayList<>();
    initHourRides();
  }

  private void validate(List<String[]> input) {
    if (input == null || input.size() <= 1) {
      throw new InvalidInputDataException("Input data doesn't contain enough information.");
    }
  }

  private void initHourRides() {
    for (int i = 0; i < Hour.HOUR_NUM; i++) {
      hourRides.add(new ConcurrentHashMap<>());
    }
  }

  public void processInput() throws InterruptedException {
    long startTime = System.currentTimeMillis();

    Producer producer = new Producer(inputData, skierQueue, liftQueue, hourQueue);
    executorService.execute(producer);

    for (int i = 0; i < NUM_THREADS; i++) {
      executorService.execute(new SkierQueueConsumer(
          skierQueue, skierNumRides, skierVerticalMeters));
      executorService.execute(new LiftQueueConsumer(liftQueue, liftNumRides));
      executorService.execute(new HourQueueConsumer(hourQueue, hourRides));
    }
    executorService.shutdown();
    executorService.awaitTermination(NUM_SECONDS_WAIT, TimeUnit.SECONDS);

    runTime = Duration.ofMillis(System.currentTimeMillis() - startTime);
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
  public List<Map<String, Integer>> getHourRides() {
    return new ArrayList<>(hourRides);
  }

  @Override
  public Duration getRunTime() {
    return runTime;
  }

  @Override
  public String toString() {
    return "Concurrent processor";
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    ConcurrentProcessor that = (ConcurrentProcessor) other;

    return inputData.equals(that.inputData);
  }

  @Override
  public int hashCode() {
    return inputData.hashCode();
  }

}
