package edu.neu.ccs.cs5010.assignment8.processors;

import edu.neu.ccs.cs5010.assignment8.lift.ILift;
import edu.neu.ccs.cs5010.assignment8.skier.ISkier;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * The IProcessor represents a processor interface.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IProcessor {
  /**
   * Processes input and stores all information .
   *
   * @throws InterruptedException when the thread is interrupted
   */
  void processInput() throws InterruptedException;

  /**
   * Gets all skiers' information.
   *
   * @return the map stores all skiers' information
   */
  Map<String, ISkier> getSkierMap();

  /**
   * Gets all lifts' information.
   *
   * @return the list stores all lifts' information
   */
  List<ILift> getLiftList();

  /**
   * Gets all hours' lifts' information.
   *
   * @return the list stores all hours' lifts' information
   */
  List<List<ILift>> getHourRides();

  /**
   * Gets the processor's runtime.
   *
   * @return Duration the actual runtime
   */
  Duration getRunTime();
}
