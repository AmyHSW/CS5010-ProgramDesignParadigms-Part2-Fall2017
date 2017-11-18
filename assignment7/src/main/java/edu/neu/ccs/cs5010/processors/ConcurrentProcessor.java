package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.pairs.HourLiftIdPair;
import edu.neu.ccs.cs5010.pairs.Pair;
import edu.neu.ccs.cs5010.pairs.SkierLiftIdPair;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class ConcurrentProcessor extends Processor {

  private static final int NUM_THREADS = 5;

  private final BlockingQueue<Pair> skierQueue;
  private final BlockingQueue<String> liftQueue;
  private final BlockingQueue<Pair> hourQueue;

  private final Executor executor = Executors.newFixedThreadPool(NUM_THREADS);;

  public ConcurrentProcessor(List<String[]> inputData) {
    skierQueue = new LinkedBlockingDeque<>();
    liftQueue = new LinkedBlockingDeque<>();
    hourQueue = new LinkedBlockingDeque<>();
    skierNumRides = new ConcurrentHashMap<>();
    skierVerticalMeters = new ConcurrentHashMap<>();
    liftNumRides = new ConcurrentHashMap<>();
    hourRides = new ConcurrentHashMap<>();
    setupQueues(inputData.subList(1, inputData.size()));
  }

  private void setupQueues(List<String[]> inputData) {
    for (String[] record : inputData) {
      skierQueue.offer(new SkierLiftIdPair(record[SKIER_INDEX], record[LIFT_INDEX]));
      liftQueue.offer(record[LIFT_INDEX]);
      int hour = (Integer.parseInt(record[TIME_INDEX]) - 1) / MINUTES_IN_HOUR + 1;
      hourQueue.offer(new HourLiftIdPair(Integer.toString(hour), record[LIFT_INDEX]));
    }
  }

  @Override
  public void processInput() {
    long startTime = System.currentTimeMillis();

    /*
    for (final Pair pair : skierQueue) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          processSkier(pair.getFirst(), pair.getLast());
        }
      });
    }
    */

    executor.execute(this::processSkier);
    executor.execute(this::processLift);
    executor.execute(this::processHour);

    //executorService.shutdown();
    System.out.println("concurrent runs " + (System.currentTimeMillis() - startTime));
  }

  private void processSkier() {
    while (!skierQueue.isEmpty()) {
      Pair pair = skierQueue.poll();
      processSkier(pair.getFirst(), pair.getLast());
    }
  }

  private void processLift() {
    while (!liftQueue.isEmpty()) {
      String lift = liftQueue.poll();
      processLift(lift);
    }
  }

  private void processHour() {
    while (!hourQueue.isEmpty()) {
      Pair pair = hourQueue.poll();
      processHour(pair.getFirst(), pair.getLast());
    }
  }
}
