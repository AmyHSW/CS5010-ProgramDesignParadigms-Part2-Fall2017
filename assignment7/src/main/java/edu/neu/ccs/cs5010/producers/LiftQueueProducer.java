package edu.neu.ccs.cs5010.producers;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * The LiftQueueProducer represents a concrete lift queue producer.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class LiftQueueProducer extends Producer {

  private static final int LIFT_INDEX = 3;

  /**
   * The constructor of HourQueueProducer.
   *
   * @param inputData list of string arrays of all ski information
   * @param liftQueue BlockingQueue that stores all lifts info
   */
  public LiftQueueProducer(List<String[]> inputData,
                           BlockingQueue<String> liftQueue) {
    super(inputData);
    this.queue = liftQueue;
    this.sentinel = "";
  }

  @Override
  public String produce(String[] record) {
    return record[LIFT_INDEX];
  }

}
