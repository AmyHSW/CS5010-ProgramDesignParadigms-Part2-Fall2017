package edu.neu.ccs.cs5010.assignment8.Record;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SkierRowRecord implements IRecord {

  private int skierId;
  private int rowIndex;

  public static final int SIZE = Integer.BYTES * 2;

  public SkierRowRecord() {
    skierId = 0;
    rowIndex = 0;
  }

  public SkierRowRecord(int skierId, int rowIndex) {
    this.skierId = skierId;
    this.rowIndex = rowIndex;
  }

  @Override
  public int getParameter() {
    return skierId;
  }

  public int getRowIndex() {
    return rowIndex;
  }

  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    skierId = file.readInt();
    rowIndex = file.readInt();
  }

  @Override
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(skierId);
    file.writeInt(rowIndex);
  }
}
