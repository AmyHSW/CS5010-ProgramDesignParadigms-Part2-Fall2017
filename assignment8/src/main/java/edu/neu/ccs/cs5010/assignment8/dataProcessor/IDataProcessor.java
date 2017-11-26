package edu.neu.ccs.cs5010.assignment8.dataProcessor;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.lift.ILift;
import edu.neu.ccs.cs5010.assignment8.skier.ISkier;

import java.time.Duration;
import java.util.List;

/**
 * The IDataProcessor represents a processor interface.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IDataProcessor {
  /**
   * Processes input and stores all information .
   *
   * @throws InterruptedException when the thread is interrupted
   */
  void processInput() throws InterruptedException;

  /**
   * Gets all skiers' detailed information.
   *
   * @return the map stores all skiers' detailed information
   */
  List<IRecord> getRawList();

  /**
   * Gets all skiers' summary information.
   *
   * @return the map stores all skiers' summary information
   */
  List<IRecord> getSkierList();

  /**
   * Gets all lifts' information.
   *
   * @return the list stores all lifts' information
   */
  List<IRecord> getLiftList();

  /**
   * Gets all hours' lifts' information.
   *
   * @return the list stores all hours' lifts' information
   */
  List<List<IRecord>> getHourRides();

  /**
   * Gets the processor's runtime.
   *
   * @return Duration the actual runtime
   */
  Duration getRunTime();
}
