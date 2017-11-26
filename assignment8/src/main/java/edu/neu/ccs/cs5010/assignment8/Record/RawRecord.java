package edu.neu.ccs.cs5010.assignment8.Record;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RawRecord implements IRecord {

  private int skierId;
  private int time;
  private int liftId;

  public static final int SIZE = Integer.BYTES * 3;

  public RawRecord() {
    skierId = 0;
    time = 0;
    liftId = 0;
  }

  public RawRecord(int skierId, int time, int liftId) {
    this.skierId = skierId;
    this.time = time;
    this.liftId = liftId;
  }

  @Override
  public int getParameter() {
    return skierId;
  }

  public int getTime() {
    return time;
  }

  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    skierId = file.readInt();
    time = file.readInt();
    liftId = file.readInt();
  }

  @Override
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(skierId);
    file.writeInt(time);
    file.writeInt(liftId);
  }

  public int compareTo(RawRecord that) {
    if (that == null) {
      throw new NullPointerException("Given RawRecord is null");
    }
    if (skierId != that.skierId) {
      return this.skierId - that.skierId;
    }
    return this.time - that.time;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    RawRecord rawRecord = (RawRecord) other;

    if (skierId != rawRecord.skierId) {
      return false;
    }
    if (time != rawRecord.time) {
      return false;
    }
    return liftId == rawRecord.liftId;
  }

  @Override
  public int hashCode() {
    int result = skierId;
    result = 31 * result + time;
    result = 31 * result + liftId;
    return result;
  }
}
