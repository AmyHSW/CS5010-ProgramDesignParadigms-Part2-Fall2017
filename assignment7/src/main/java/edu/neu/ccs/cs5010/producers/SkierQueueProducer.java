package edu.neu.ccs.cs5010.producers;

import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class SkierQueueProducer extends Producer {

  private static final int SKIER_INDEX = 2;
  private static final int LIFT_INDEX = 3;

  public SkierQueueProducer(List<String[]> inputData,
                            BlockingQueue<IPair> skierQueue) {
    this.inputData = inputData;
    this.queue = skierQueue;
    this.sentinel = new Pair("", "");
  }

  @Override
  public IPair produce(String[] record) {
    return new Pair(record[SKIER_INDEX], record[LIFT_INDEX]);
  }
}
