package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.record.HourRecord;
import edu.neu.ccs.cs5010.assignment8.record.IRecord;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The HoursReader class reads the hours dat file to find the result of a query.
 * The RandomAccessFile is used to find the position of the record, so that the dat
 * file can be read in O(1) time.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class HoursReader implements IReader {

  private static final String HOUR_DATA_FILE = "hours.dat";
  private final int parameter;
  private final RandomAccessFile file;

  /**
   * Constructs a new hours reader with the parameter of query.
   * @param parameter the parameter of a query
   * @throws IOException if there is an I/O failure
   */
  public HoursReader(int parameter)throws IOException {
    this.parameter = parameter;
    file = new RandomAccessFile(HOUR_DATA_FILE, "r");
  }

  /**
   * Returns the result of query by reading the dat file.
   * @return the result of query.
   * @throws IOException if there is an I/O failure.
   */
  @Override
  public String read() throws IOException {
    IRecord record = new HourRecord();
    file.seek((parameter - 1) * HourRecord.SIZE);
    record.readFromFile(file);
    file.close();
    return record.toString();
  }
}
