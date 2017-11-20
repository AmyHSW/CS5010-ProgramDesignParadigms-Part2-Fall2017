package edu.neu.ccs.cs5010.lift;

/**
 * The ILift represents a lift interface.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface ILift extends Comparable<ILift> {
  /**
   * Increments the rides number.
   */
  void incrementNumber();

  /**
   * Gets the lift id.
   *
   * @return the lift id represented by a string
   */
  String getLiftId();

  /**
   * Gets the number of rides the lift holds.
   *
   * @return the number of rides.
   */
  int getNumber();
}
