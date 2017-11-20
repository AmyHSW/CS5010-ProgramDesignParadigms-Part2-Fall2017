package edu.neu.ccs.cs5010.producers;

/**
 * The IProducer represents a producer interface.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IProducer<T> extends Runnable {
  /**
   * Starts producing elements.
   *
   * @param record string array that contains one line information from the csv file
   */
  T produce(String[] record);
}
