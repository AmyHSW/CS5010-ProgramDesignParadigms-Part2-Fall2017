package edu.neu.ccs.cs5010.consumers;

/**
 * The IConsumer represents a consumer interface.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IConsumer extends Runnable {
  /**
   * Starts consuming elements.
   */
  void consume();
}
