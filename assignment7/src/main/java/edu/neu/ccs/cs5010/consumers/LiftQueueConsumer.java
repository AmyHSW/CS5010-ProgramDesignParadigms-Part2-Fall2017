package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.lift.ILift;
import edu.neu.ccs.cs5010.lift.Lift;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * The LiftQueueConsumer represents a consumer for lift queue.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class LiftQueueConsumer extends Consumer {

  private final List<ILift> liftList;

  /**
   * The constructor of LiftQueueConsumer
   *
   */
  public LiftQueueConsumer(BlockingQueue<String> liftQueue,
                           List<ILift> liftList) {
    this.queue = liftQueue;
    this.liftList = liftList;
    this.sentinel = "";
  }

  @Override
  public void consume() {
    liftList.get(Lift.toIndex((String)item)).incrementNumber();
  }
}
