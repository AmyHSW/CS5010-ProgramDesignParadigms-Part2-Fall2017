package edu.neu.ccs.cs5010.assignment8.record;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The RawRecord class represents a raw lift ride record.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class RawRecord implements IRecord {

  /**
   * The size of one raw record in the dat file.
   */
  public static final int SIZE = Integer.BYTES * 3;

  private int skierId;
  private int time;
  private int liftId;

  /**
   * The default constructor of the raw record.
   */
  public RawRecord() {
    skierId = 0;
    time = 0;
    liftId = 0;
  }

  /**
   * Constructs a new raw record with the skier id, time and lift id.
   * @param skierId the skier id
   * @param time the time stamp
   * @param liftId the lift id
   */
  public RawRecord(int skierId, int time, int liftId) {
    this.skierId = skierId;
    this.time = time;
    this.liftId = liftId;
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
   * Returns the time stamp of this lift ride.
   * @return the time stamp of this lift ride.
   */
  public int getTime() {
    return time;
  }

  /**
   * Reads from the dat file to update the fields of this record.
   * @param file the dat file
   * @throws IOException if there is an I/O failure
   */
  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    skierId = file.readInt();
    time = file.readInt();
    liftId = file.readInt();
  }

  /**
   * Writes the information of this record to the file.
   * @param file the dat file.
   * @throws IOException if there is an I/O failure.
   */
  @Override
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(skierId);
    file.writeInt(time);
    file.writeInt(liftId);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

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

  @Override
  public String toString() {
    return "[" + time + "," + liftId + "]";
  }
}
