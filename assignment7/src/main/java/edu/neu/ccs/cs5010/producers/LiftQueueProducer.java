package edu.neu.ccs.cs5010.producers;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class LiftQueueProducer extends Producer {

  private static final int LIFT_INDEX = 3;

  public LiftQueueProducer(List<String[]> inputData,
                           BlockingQueue<String> liftQueue) {
    this.inputData = inputData;
    this.queue = liftQueue;
    this.sentinel = "";
  }

  @Override
  public String produce(String[] record) {
    return record[LIFT_INDEX];
  }

}
