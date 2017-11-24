package edu.neu.ccs.cs5010.assignment8.skier;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Skier represents a concrete skier .
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class Skier implements ISkier {

  public static final int SKIER_TOTAL = 40000;
  private final String skierId;
  private AtomicInteger numRides;
  private AtomicInteger verticalMeters;

  /**
   * The constructor of Skier.
   *
   * @param skierIndex the index of Skier object in skierList
   */
  public Skier(int skierIndex) {
    this.skierId = Integer.toString(skierIndex + 1);
    numRides = new AtomicInteger(0);
    verticalMeters = new AtomicInteger(0);
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
  public int getNumRides() {
    return numRides.get();
  }

  @Override
  public int getVerticalMeters() {
    return verticalMeters.get();
  }

  public static int toIndex(String string) {
    return Integer.parseInt(string) - 1;
  }

  @Override
  public int compareTo(ISkier that) {
    if (that == null) {
      throw new NullPointerException("Given Skier is null");
    }
    return that.getVerticalMeters() - verticalMeters.get();
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
