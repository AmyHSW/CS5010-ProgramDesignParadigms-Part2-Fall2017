package edu.neu.ccs.cs5010;

public class LiftToHeightConverter {

  private static final String LIFT_LEVEL1 = "10";
  private static final String LIFT_LEVEL2 = "20";
  private static final String LIFT_LEVEL3 = "30";
  private static final int LIFT_LEVEL1_HEIGHT = 200;
  private static final int LIFT_LEVEL2_HEIGHT = 300;
  private static final int LIFT_LEVEL3_HEIGHT = 400;
  private static final int LIFT_LEVEL4_HEIGHT = 500;

  public static int toHeight(String lift) {
    if (lift.compareTo(LIFT_LEVEL1) <= 0) {
      return LIFT_LEVEL1_HEIGHT;
    } else if (lift.compareTo(LIFT_LEVEL2) <= 0) {
      return LIFT_LEVEL2_HEIGHT;
    } else if (lift.compareTo(LIFT_LEVEL3) <= 0) {
      return LIFT_LEVEL3_HEIGHT;
    } else {
      return LIFT_LEVEL4_HEIGHT;
    }
  }
}
