package edu.neu.ccs.cs5010.assignment8.lift;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Lift represents a concrete lift.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class Lift implements ILift {

  public static final int LIFT_TOTAL = 40;
  private static final int LIFT_LEVEL1 = 10;
  private static final int LIFT_LEVEL2 = 20;
  private static final int LIFT_LEVEL3 = 30;
  private static final int LIFT_LEVEL1_HEIGHT = 200;
  private static final int LIFT_LEVEL2_HEIGHT = 300;
  private static final int LIFT_LEVEL3_HEIGHT = 400;
  private static final int LIFT_LEVEL4_HEIGHT = 500;

  private final String liftId;
  private AtomicInteger number;

  /**
   * The constructor of Lift object.
   *
   * @param liftIndex the lift index in the list
   * @throws IllegalArgumentException if the index is out of bound
   */
  public Lift(int liftIndex) {
    if (liftIndex >= LIFT_TOTAL) {
      throw new IllegalArgumentException("Lift index out of bound.");
    }
    liftId = Integer.toString(liftIndex + 1);
    number = new AtomicInteger(0);
  }

  @Override
  public void incrementNumber() {
    number.incrementAndGet();
  }

  @Override
  public String getLiftId() {
    return liftId;
  }

  @Override
  public int getNumber() {
    return number.get();
  }

  /**
   * Converts the lift ID to the lift index in the list.
   *
   * @param liftId the lift ID represented by a string
   * @return the lift index in the list
   */
  public static int toIndex(String liftId) {
    return Integer.parseInt(liftId) - 1;
  }

  /**
   * Converts the lift ID to the lift vertical meters.
   *
   * @param lift the lift ID represented by a string
   * @return the lift vertical meters
   */
  public static int toVerticalMeters(String lift) {
    int liftId = Integer.parseInt(lift);
    if (liftId <= LIFT_LEVEL1) {
      return LIFT_LEVEL1_HEIGHT;
    } else if (liftId <= LIFT_LEVEL2) {
      return LIFT_LEVEL2_HEIGHT;
    } else if (liftId <= LIFT_LEVEL3) {
      return LIFT_LEVEL3_HEIGHT;
    } else {
      return LIFT_LEVEL4_HEIGHT;
    }
  }

  @Override
  public int compareTo(ILift that) {
    if (that == null) {
      throw new NullPointerException("Given Lift is null");
    }
    return that.getNumber() - number.get();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Lift lift = (Lift) other;

    return liftId.equals(lift.liftId);
  }

  @Override
  public int hashCode() {
    return liftId.hashCode();
  }
}
