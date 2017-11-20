package edu.neu.ccs.cs5010.lift;

import java.util.concurrent.atomic.AtomicInteger;

public class Lift implements ILift {

  private static final int LIFT_LEVEL1 = 10;
  private static final int LIFT_LEVEL2 = 20;
  private static final int LIFT_LEVEL3 = 30;
  private static final int LIFT_LEVEL1_HEIGHT = 200;
  private static final int LIFT_LEVEL2_HEIGHT = 300;
  private static final int LIFT_LEVEL3_HEIGHT = 400;
  private static final int LIFT_LEVEL4_HEIGHT = 500;

  private final String liftId;
  private AtomicInteger number;

  public Lift(String liftId) {
    validate(liftId);
    this.liftId = liftId;
    number = new AtomicInteger(0);
  }

  private void validate(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Lift id is null.");
    }
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
}
