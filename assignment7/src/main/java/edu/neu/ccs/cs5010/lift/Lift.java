package edu.neu.ccs.cs5010.lift;

import edu.neu.ccs.cs5010.skier.ISkier;
import edu.neu.ccs.cs5010.skier.Skier;

import java.util.concurrent.atomic.AtomicInteger;

public class Lift implements ILift {

  private static final int LIFT_LEVEL1 = 10;
  private static final int LIFT_LEVEL2 = 20;
  private static final int LIFT_LEVEL3 = 30;
  private static final int LIFT_LEVEL1_HEIGHT = 200;
  private static final int LIFT_LEVEL2_HEIGHT = 300;
  private static final int LIFT_LEVEL3_HEIGHT = 400;
  private static final int LIFT_LEVEL4_HEIGHT = 500;
  public static final int LIFT_NUM = 40;

  private final String liftId;
  private AtomicInteger number;

  public Lift(String liftId) {
    if (liftId == null) {
      throw new IllegalArgumentException("Lift id is null.");
    }
    this.liftId = liftId;
    number = new AtomicInteger(0);
  }

  public Lift(int liftIndex) {
    if (liftIndex >= LIFT_NUM) {
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

  public static int toIndex(String liftId) {
    return Integer.parseInt(liftId) - 1;
  }

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
