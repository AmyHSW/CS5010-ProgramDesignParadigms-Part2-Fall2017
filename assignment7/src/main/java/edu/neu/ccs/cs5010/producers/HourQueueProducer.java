package edu.neu.ccs.cs5010.producers;

import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * The HourQueueProducer represents a concrete hour queue producer.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class HourQueueProducer extends Producer {

  private static final int LIFT_INDEX = 3;
  private static final int TIME_INDEX = 4;

  /**
   * The constructor of HourQueueProducer.
   *
   * @param inputData list of string arrays of all ski information
   * @param hourQueue BlockingQueue that stores all hours info
   */
  public HourQueueProducer(List<String[]> inputData,
                           BlockingQueue<IPair> hourQueue) {
    super(inputData);
    this.queue = hourQueue;
    this.sentinel = new Pair("", "");
  }

  @Override
  public IPair produce(String[] record) {
    return new Pair(record[TIME_INDEX], record[LIFT_INDEX]);
  }
}
