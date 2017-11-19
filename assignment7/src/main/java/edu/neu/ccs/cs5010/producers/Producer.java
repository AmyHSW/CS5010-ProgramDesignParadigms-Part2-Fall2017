package edu.neu.ccs.cs5010.producers;

import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

  private static final IPair SENTINEL = new Pair("", "");
  private static final String LIFT_SENTINEL = "";
  private static final int SKIER_INDEX = 2;
  private static final int LIFT_INDEX = 3;
  private static final int TIME_INDEX = 4;
  private final List<String[]> inputData;
  private final BlockingQueue<IPair> skierQueue;
  private final BlockingQueue<String> liftQueue;
  private final BlockingQueue<IPair> hourQueue;

  public Producer(List<String[]> inputData,
                  BlockingQueue<IPair> skierQueue,
                  BlockingQueue<String> liftQueue,
                  BlockingQueue<IPair> hourQueue) {
    this.inputData = inputData;
    this.skierQueue = skierQueue;
    this.liftQueue = liftQueue;
    this.hourQueue = hourQueue;
  }

  @Override
  public void run() {
    try {
      for (String[] record : inputData) {
        skierQueue.put(produceSkierLiftPair(record));
        liftQueue.put(record[LIFT_INDEX]);
        hourQueue.put(produceHourLiftPair(record));
      }
      skierQueue.put(SENTINEL);
      liftQueue.put(LIFT_SENTINEL);
      hourQueue.put(SENTINEL);
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
  }

  private IPair produceSkierLiftPair(String[] record) {
    return new Pair(record[SKIER_INDEX], record[LIFT_INDEX]);
  }

  private IPair produceHourLiftPair(String[] record) {
    return new Pair(record[TIME_INDEX], record[LIFT_INDEX]);
  }
}
