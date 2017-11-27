package edu.neu.ccs.cs5010.assignment8.record;

import edu.neu.ccs.cs5010.assignment8.exception.InvalidLiftIdException;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The LiftRecord class represents a lift record.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class LiftRecord implements IRecord {

  /**
   * The size of each lift record in the dat file.
   */
  public static final int SIZE = Integer.BYTES * 2;
  /**
   * The total number of lifts.
   */
  public static final int LIFT_TOTAL = 40;

  private static final int LIFT_LEVEL1 = 10;
  private static final int LIFT_LEVEL2 = 20;
  private static final int LIFT_LEVEL3 = 30;
  private static final int LIFT_LEVEL1_HEIGHT = 200;
  private static final int LIFT_LEVEL2_HEIGHT = 300;
  private static final int LIFT_LEVEL3_HEIGHT = 400;
  private static final int LIFT_LEVEL4_HEIGHT = 500;

  private int liftId;
  private int number;

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
   * @param liftId the lift id
   * @throws IllegalArgumentException if the index is out of bound
   */
  public LiftRecord(int liftId) {
    if (liftId <= 0 || liftId > LIFT_TOTAL) {
      throw new InvalidLiftIdException("Lift index out of bound.");
    }
    this.liftId = liftId;
    number = 0;
  }

  /**
   * Increments the number of rides by one.
   */
  public void incrementNumber() {
    number++;
  }

  /**
   * Returns the lift id.
   * @return the lift id.
   */
  @Override
  public int getParameter() {
    return liftId;
  }

  /**
   * Returns the number of rides.
   * @return the number of rides.
   */
  public int getNumber() {
    return number;
  }

  /**
   * Reads from the dat file to update the fields of this record.
   * @param file the dat file
   * @throws IOException if there is an I/O failure
   */
  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    liftId = file.readInt();
    number = file.readInt();
  }

  /**
   * Writes the information of this record to the file.
   * @param file the dat file.
   * @throws IOException if there is an I/O failure.
   */
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

  @Override
  public String toString() {
    return Integer.toString(liftId) + "," + Integer.toString(number);
  }
}
