package edu.neu.ccs.cs5010.assignment8.skier;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SkierRecord {
  private int skierId;
  private int numRides;
  private int totalVertical;
  private int numberOfViews;

  public static final int SIZE = Integer.BYTES * 4;
  public static final int POS_TO_VIEWS = Integer.BYTES * 3;

  public SkierRecord(int skierId, int numRides, int totalVertical) {
    this.skierId = skierId;
    this.numRides = numRides;
    this.totalVertical = totalVertical;
    numberOfViews = 0;
  }

  public void readFromFile(RandomAccessFile file) throws IOException {
    skierId = file.readInt();
    numRides = file.readInt();
    totalVertical = file.readInt();
    numberOfViews = file.readInt();
  }

  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(skierId);
    file.writeInt(numRides);
    file.writeInt(totalVertical);
    file.writeInt(numberOfViews);
  }

  public void updateNumberOfViewsToFile(RandomAccessFile file)
          throws IOException {
    file.seek((skierId - 1) * SkierRecord.SIZE + POS_TO_VIEWS);
    file.writeInt(numberOfViews + 1);
  }

  public int getSkierId() {
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
}
