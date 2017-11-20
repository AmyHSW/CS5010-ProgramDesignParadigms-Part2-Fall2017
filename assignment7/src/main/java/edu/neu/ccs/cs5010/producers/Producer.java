package edu.neu.ccs.cs5010.producers;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * The Producer represents a concrete producer.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public abstract class Producer<T> implements IProducer {

  protected BlockingQueue<T> queue;
  protected T sentinel;
  protected List<String[]> inputData;

  /**
   * The constructor of Producer.
   *
   * @param inputData list of string arrays of all ski information
   */
  public Producer(List<String[]> inputData) {
    this.inputData = inputData;
  }

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
