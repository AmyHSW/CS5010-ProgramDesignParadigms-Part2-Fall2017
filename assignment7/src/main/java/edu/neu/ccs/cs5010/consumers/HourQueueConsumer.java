package edu.neu.ccs.cs5010.consumers;

import edu.neu.ccs.cs5010.lift.Hour;
import edu.neu.ccs.cs5010.lift.ILift;
import edu.neu.ccs.cs5010.lift.Lift;
import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.pairs.Pair;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * The HourQueueConsumer represents a consumer for hour queue.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class HourQueueConsumer extends Consumer {

  private final List<List<ILift>> hourRides;

  /**
   * The constructor of HourQueueConsumer.
   *
   * @param hourQueue BlockingQueue that stores all hours info
   * @param hourRides list that stores each hour's each lift's information
   */
  public HourQueueConsumer(BlockingQueue<IPair> hourQueue,
                          List<List<ILift>> hourRides) {
    this.queue = hourQueue;
    this.hourRides = hourRides;
    this.sentinel = new Pair("", "");
  }

  @Override
  public void consume() {
    IPair pair = (IPair) item;
    int hourIndex = Hour.toIndex(pair.getFirst());
    int liftIndex = Lift.toIndex(pair.getLast());
    ILift theLift = hourRides.get(hourIndex).get(liftIndex);
    theLift.incrementNumber();
  }

}
