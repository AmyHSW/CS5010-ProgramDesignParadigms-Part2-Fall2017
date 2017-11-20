package edu.neu.ccs.cs5010.consumers;

import java.util.concurrent.BlockingQueue;

/**
 * The Consumer represents a concrete consumer interface.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public abstract class Consumer<T> implements IConsumer {

  protected T sentinel;
  protected BlockingQueue<T> queue;
  protected T item;

  @Override
  public void run() {
    try {
      while (true) {
        item = queue.take();
        if (item.equals(sentinel)) {
          queue.add(sentinel);
          return;
        }
        consume();
      }
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public abstract void consume();
}
