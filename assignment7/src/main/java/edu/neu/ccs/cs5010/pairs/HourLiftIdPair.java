package edu.neu.ccs.cs5010.pairs;

public class HourLiftIdPair implements Pair {

  private final String hour;
  private final String liftId;

  public HourLiftIdPair(String hour, String liftId) {
    this.hour = hour;
    this.liftId = liftId;
  }

  @Override
  public String getFirst() {
    return hour;
  }

  @Override
  public String getLast() {
    return liftId;
  }
}
