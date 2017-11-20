package edu.neu.ccs.cs5010.skier;

/**
 * The ISkier represents a skier interface.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface ISkier extends Comparable<ISkier> {
  /**
   * Increments the rides number.
   */
  void incrementNumRides();

  /**
   * Increase the vertical meters of the skier.
   *
   * @param delta the vertical meters to increase
   */
  void increaseVerticalMeters(int delta);

  /**
   * Gets the skier id.
   *
   * @return the skier id represented by a string
   */
  String getSkierId();


  /**
   * Gets the number of rides the skier holds.
   *
   * @return the number of rides.
   */
  int getNumRides();

  /**
   * Gets the vertical meters the skier holds.
   *
   * @return the vertical meters.
   */
  int getVerticalMeters();
}
