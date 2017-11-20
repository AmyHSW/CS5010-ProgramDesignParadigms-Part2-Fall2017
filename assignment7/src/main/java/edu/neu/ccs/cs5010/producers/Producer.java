package edu.neu.ccs.cs5010.producers;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public abstract class Producer<T> implements IProducer {

  protected BlockingQueue<T> queue;
  protected T sentinel;
  protected List<String[]> inputData;

  @Override
  public void run() {
    try {
      for (String[] record : inputData) {
        queue.put(produce(record));
      }
      queue.put(sentinel);
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public abstract T produce(String[] record);

}
