package edu.neu.ccs.cs5010.pairs;

public class SkierLiftIdPair implements Pair {

  private final String skierId;
  private final String liftId;

  public SkierLiftIdPair(String skierId, String liftId) {
    this.skierId = skierId;
    this.liftId = liftId;
  }

  @Override
  public String getFirst() {
    return skierId;
  }

  @Override
  public String getLast() {
    return liftId;
  }
}
