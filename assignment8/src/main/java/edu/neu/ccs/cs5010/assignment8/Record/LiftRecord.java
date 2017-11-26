package edu.neu.ccs.cs5010.assignment8.Record;

import java.io.IOException;
import java.io.RandomAccessFile;

public class LiftRecord implements IRecord {

  private int liftId;
  private int number;

  public static final int LIFT_TOTAL = 40;
  public static final int SIZE = Integer.BYTES * 2;

  private static final int LIFT_LEVEL1 = 10;
  private static final int LIFT_LEVEL2 = 20;
  private static final int LIFT_LEVEL3 = 30;
  private static final int LIFT_LEVEL1_HEIGHT = 200;
  private static final int LIFT_LEVEL2_HEIGHT = 300;
  private static final int LIFT_LEVEL3_HEIGHT = 400;
  private static final int LIFT_LEVEL4_HEIGHT = 500;


  /**
   * The default constructor of LiftRecord object.
   */
  public LiftRecord() {
    liftId = 0;
    number = 0;
  }

  /**
   * The constructor of LiftRecord object.
   *
   * @param liftIndex the lift index in the list
   * @throws IllegalArgumentException if the index is out of bound
   */
  public LiftRecord(int liftIndex) {
    if (liftIndex >= LIFT_TOTAL) {
      throw new IllegalArgumentException("Lift index out of bound.");
    }
    liftId = liftIndex + 1;
    number = 0;
  }

  public void incrementNumber() {
    number++;
  }

  public int getParameter() {
    return liftId;
  }

  public int getNumber() {
    return number;
  }

  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    liftId = file.readInt();
    number = file.readInt();
  }

  @Override
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(liftId);
    file.writeInt(number);
  }

  /**
   * Converts the lift ID to the lift vertical meters.
   *
   * @param liftId the lift ID
   * @return the lift vertical meters
   */
  public static int toVerticalMeters(int liftId) {
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

  public int compareTo(LiftRecord that) {
    if (that == null) {
      throw new NullPointerException("Given Lift is null");
    }
    return that.getNumber() - number;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    LiftRecord lift = (LiftRecord) other;

    return liftId == lift.liftId;
  }

  @Override
  public int hashCode() {
    return liftId;
  }
}
