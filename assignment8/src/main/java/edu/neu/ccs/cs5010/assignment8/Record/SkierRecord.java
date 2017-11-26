package edu.neu.ccs.cs5010.assignment8.Record;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SkierRecord implements IRecord {
  private int skierId;
  private int numRides;
  private int totalVertical;
  private int numberOfViews;

  public static final int SIZE = Integer.BYTES * 4;
  public static final int POS_TO_VIEWS = Integer.BYTES * 3;
  public static final int SKIER_TOTAL = 40000;

  /**
   * The default constructor of SkierRecord.
   */
  public SkierRecord() {
    skierId = 0;
    numRides = 0;
    totalVertical = 0;
    numberOfViews = 0;
  }

  /**
   * The constructor of SkierRecord.
   *
   * @param skierIndex the index of Skier object in skierList
   */
  public SkierRecord(int skierIndex) {
    skierId = skierIndex + 1;
    numRides = 0;
    totalVertical = 0;
    numberOfViews = 0;
  }

  public void incrementNumRides() {
    numRides++;
  }

  public void increaseTotalVertical(int delta) {
    totalVertical += delta;
  }

  @Override
  public int getParameter() {
    return skierId;
  }

  public int getNumRides() {
    return numRides;
  }

  public int getTotalVertical() {
    return totalVertical;
  }

  public int getNumberOfViews() {
    return numberOfViews;
  }

  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    skierId = file.readInt();
    numRides = file.readInt();
    totalVertical = file.readInt();
    numberOfViews = file.readInt() + 1;
  }

  @Override
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(skierId);
    file.writeInt(numRides);
    file.writeInt(totalVertical);
    file.writeInt(numberOfViews);
  }

  public void updateNumberOfViewsToFile(RandomAccessFile file)
          throws IOException {
    file.seek((skierId - 1) * SkierRecord.SIZE + POS_TO_VIEWS);
    file.writeInt(numberOfViews);
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
}
