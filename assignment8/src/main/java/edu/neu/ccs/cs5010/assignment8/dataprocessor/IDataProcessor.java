package edu.neu.ccs.cs5010.assignment8.dataprocessor;

import edu.neu.ccs.cs5010.assignment8.record.IRecord;

import java.time.Duration;
import java.util.List;

/**
 * The IDataProcessor represents a data processor interface.
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
   * @return the list that stores all skiers' detailed information
   */
  List<IRecord> getRawList();

  /**
   * Gets all skiers' summary information.
   *
   * @return the list that stores all skiers' summary information
   */
  List<IRecord> getSkierList();

  /**
   * Gets all lifts' information.
   *
   * @return the list that stores all lifts' information
   */
  List<IRecord> getLiftList();

  /**
   * Gets all hours' information.
   *
   * @return the list that stores all hours' information
   */
  List<IRecord> getHourList();

  /**
   * Gets all skiers' row indices information.
   *
   * @return the list stores all skiers' row indices information
   */
  List<IRecord> getSkierRowList();

  /**
   * Gets the processor's runtime.
   *
   * @return Duration the actual runtime
   */
  Duration getRunTime();
}
