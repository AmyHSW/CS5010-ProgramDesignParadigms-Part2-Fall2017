package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.lift.Lift;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * The LiftQueueConsumer represents a consumer for lift queue.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class LiftQueueConsumer extends Consumer {

  private final List<Lift> liftList;

  /**
   * The constructor of LiftQueueConsumer
   *
   */
  public LiftQueueConsumer(BlockingQueue<String> liftQueue,
                           List<Lift> liftList) {
    this.queue = liftQueue;
    this.liftList = liftList;
    this.sentinel = "";
  }

  @Override
  public void consume() {
    int liftId = Integer.parseInt((String) item);
    liftList.get(liftId).incrementNumber();
  }
}
