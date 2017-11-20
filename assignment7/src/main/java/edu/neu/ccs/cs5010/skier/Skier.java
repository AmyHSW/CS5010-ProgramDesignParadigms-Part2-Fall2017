package edu.neu.ccs.cs5010.skier;

import java.util.concurrent.atomic.AtomicInteger;

public class Skier implements ISkier {

  private final String skierId;
  private AtomicInteger numRides;
  private AtomicInteger verticalMeters;

  public Skier(String skierId) {
    validate(skierId);
    this.skierId = skierId;
    numRides = new AtomicInteger(0);
    verticalMeters = new AtomicInteger(0);
  }

  private void validate(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Skier id is null.");
    }
  }

  @Override
  public void incrementNumRides() {
    numRides.incrementAndGet();
  }

  @Override
  public void increaseVerticalMeters(int delta) {
    verticalMeters.addAndGet(delta);
  }

  @Override
  public String getSkierId() {
    return skierId;
  }

  @Override
  public int getVerticalMeters() {
    return verticalMeters.get();
  }

  @Override
  public int compareTo(ISkier that) {
    int thisTotal = this.verticalMeters.get();
    int thatTotal = that.getVerticalMeters();
    return thatTotal - thisTotal;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Skier skier = (Skier) other;

    return skierId.equals(skier.skierId);
  }

  @Override
  public int hashCode() {
    return skierId.hashCode();
  }
}
