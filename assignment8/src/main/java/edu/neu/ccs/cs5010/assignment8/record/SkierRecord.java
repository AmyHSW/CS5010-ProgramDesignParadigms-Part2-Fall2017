package edu.neu.ccs.cs5010.assignment8.record;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The SkierRecord class represents a skier record.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SkierRecord implements IRecord {

  /**
   * The size of each skier record in dat file.
   */
  public static final int SIZE = Integer.BYTES * 4;
  /**
   * The total number of skiers.
   */
  public static final int SKIER_TOTAL = 40000;

  private static final int POS_TO_VIEWS = Integer.BYTES * 3;

  private int skierId;
  private int numRides;
  private int totalVertical;
  private int numViews;

  /**
   * The default constructor of SkierRecord.
   */
  public SkierRecord() {
    skierId = 0;
    numRides = 0;
    totalVertical = 0;
    numViews = 0;
  }

  /**
   * The constructor of SkierRecord.
   *
   * @param skierId the skier id.
   */
  public SkierRecord(int skierId) {
    this.skierId = skierId;
    numRides = 0;
    totalVertical = 0;
    numViews = 0;
  }

  /**
   * Increments the number of rides done by this skier by one.
   */
  public void incrementNumRides() {
    numRides++;
  }

  /**
   * Increases the total vertical meters done by this skier.
   * @param delta the vertical meters to be added
   */
  public void increaseTotalVertical(int delta) {
    totalVertical += delta;
  }

  /**
   * Returns the skier id.
   * @return the skier id.
   */
  @Override
  public int getParameter() {
    return skierId;
  }

  /**
   * Returns the number of rides done by this skier.
   * @return the number of rides done by this skier.
   */
  public int getNumRides() {
    return numRides;
  }

  /**
   * Returns the total vertical meters done by this skier.
   * @return the total vertical meters done by this skier.
   */
  public int getTotalVertical() {
    return totalVertical;
  }

  /**
   * Reads from the dat file to update the fields of this record.
   * @param file the dat file
   * @throws IOException if there is an I/O failure
   */
  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    skierId = file.readInt();
    numRides = file.readInt();
    totalVertical = file.readInt();
    numViews = file.readInt();
  }

  /**
   * Writes the information of this record to the file.
   * @param file the dat file.
   * @throws IOException if there is an I/O failure.
   */
  @Override
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(skierId);
    file.writeInt(numRides);
    file.writeInt(totalVertical);
    file.writeInt(numViews);
  }

  /**
   * Updates the number of time this skier views the summary data.
   * @param file the skiers summary dat file
   * @throws IOException if there is an I/O failure
   */
  public void updateNumberOfViewsToFile(RandomAccessFile file) throws IOException {
    file.seek((skierId - 1) * SkierRecord.SIZE + POS_TO_VIEWS);
    file.writeInt(++numViews);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    SkierRecord that = (SkierRecord) other;

    return skierId == that.skierId;
  }

  @Override
  public int hashCode() {
    return skierId;
  }

  @Override
  public String toString() {
    return Integer.toString(skierId) + ","
        + Integer.toString(numRides) + ","
        + Integer.toString(totalVertical) + ","
        + Integer.toString(numViews);
  }
}
