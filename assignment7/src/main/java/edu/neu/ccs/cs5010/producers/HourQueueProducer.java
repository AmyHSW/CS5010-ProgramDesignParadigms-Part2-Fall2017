package edu.neu.ccs.cs5010.producers;

import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class HourQueueProducer extends Producer {

  private static final int LIFT_INDEX = 3;
  private static final int TIME_INDEX = 4;

  public HourQueueProducer(List<String[]> inputData,
                           BlockingQueue<IPair> hourQueue) {
    this.queue = hourQueue;
    this.inputData = inputData;
    this.sentinel = new Pair("", "");
  }

  @Override
  public IPair produce(String[] record) {
    return new Pair(record[TIME_INDEX], record[LIFT_INDEX]);
  }
}
