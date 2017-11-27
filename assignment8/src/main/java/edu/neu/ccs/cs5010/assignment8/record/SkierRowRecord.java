package edu.neu.ccs.cs5010.assignment8.record;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The SkierRowRecord class represents a record for the starting row index of each skier
 * in the raw dat file.
 *
 * <p>For example, if row 200 to row 219 are lift rides of skier #6 in the raw dat file
 * (liftRides.dat), then a record of (skier id = 6, row index = 200) will be stored as a
 * skier row record in the skier row file (skierRow.dat).
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SkierRowRecord implements IRecord {

  /**
   * The size of one skier row record in the dat file.
   */
  public static final int SIZE = Integer.BYTES * 2;

  private int skierId;
  private int rowIndex;

  /**
   * The default constructor of the skier row record.
   */
  public SkierRowRecord() {
    skierId = 0;
    rowIndex = 0;
  }

  /**
   * Constructs a new skier row record with the skier id and the row index.
   * @param skierId the skier id
   * @param rowIndex the row index
   */
  public SkierRowRecord(int skierId, int rowIndex) {
    this.skierId = skierId;
    this.rowIndex = rowIndex;
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
   * Returns the starting row index of the skier records in raw file.
   * @return the starting row index of the skier records in raw file.
   */
  public int getRowIndex() {
    return rowIndex;
  }

  /**
   * Reads from the dat file to update the fields of this record.
   * @param file the dat file
   * @throws IOException if there is an I/O failure
   */
  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    skierId = file.readInt();
    rowIndex = file.readInt();
  }

  /**
   * Writes the information of this record to the file.
   * @param file the dat file.
   * @throws IOException if there is an I/O failure.
   */
  @Override
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(skierId);
    file.writeInt(rowIndex);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    SkierRowRecord that = (SkierRowRecord) other;

    return skierId == that.skierId;
  }

  @Override
  public int hashCode() {
    return skierId;
  }
}
