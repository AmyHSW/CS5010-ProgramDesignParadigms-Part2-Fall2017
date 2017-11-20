package edu.neu.ccs.cs5010.lift;

public class Hour {
  private static final int MINUTES_IN_HOUR = 60;
  public static final int HOUR_NUM = 6;

  public static int toIndex(String time) {
    return (Integer.parseInt(time) - 1) / MINUTES_IN_HOUR;
  }
}
