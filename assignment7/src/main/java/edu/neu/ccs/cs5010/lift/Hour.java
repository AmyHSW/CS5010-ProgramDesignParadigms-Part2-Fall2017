package edu.neu.ccs.cs5010.lift;

/**
 * The Hour represents an hour class.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class Hour {
  private static final int MINUTES_IN_HOUR = 60;
  public static final int HOUR_NUM = 6;

  /**
   * Converts the time to hour index in the list.
   *
   * @param time the minutes after 9 am
   */
  public static int toIndex(String time) {
    return (Integer.parseInt(time) - 1) / MINUTES_IN_HOUR;
  }
}
