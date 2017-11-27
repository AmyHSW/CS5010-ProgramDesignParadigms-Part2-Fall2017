package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.record.RawRecord;
import edu.neu.ccs.cs5010.assignment8.record.SkierRowRecord;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The RawReader class reads the hours dat file to find the result of a query.
 * The RandomAccessFile is used to find the position of the record, so that the dat
 * file can be read in O(1) time.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class RawReader implements IReader {

  private static final String RAW_DATA_FILE = "liftRides.dat";
  private static final String SKIER_ROW_FILE = "skierRow.dat";
  private final int parameter;
  private final RandomAccessFile rawFile;
  private final RandomAccessFile skierRowFile;

  /**
   * Constructs a new raw reader with the parameter of query.
   * @param parameter the parameter of a query
   * @throws IOException if there is an I/O failure
   */
  public RawReader(int parameter) throws IOException {
    this.parameter = parameter;
    skierRowFile = new RandomAccessFile(SKIER_ROW_FILE, "r");
    rawFile = new RandomAccessFile(RAW_DATA_FILE, "r");
  }

  /**
   * Returns the result of query by reading the dat file.
   * @return the result of query.
   * @throws IOException if there is an I/O failure.
   */
  @Override
  public String read() throws IOException {
    SkierRowRecord skierRowRecord = new SkierRowRecord();
    StringBuilder stringBuilder = new StringBuilder();
    skierRowFile.seek((parameter - 1) * SkierRowRecord.SIZE);
    skierRowRecord.readFromFile(skierRowFile);
    final int row1 = skierRowRecord.getRowIndex();
    stringBuilder.append(skierRowRecord.getParameter()).append(":");
    skierRowRecord.readFromFile(skierRowFile);
    final int row2 = skierRowRecord.getRowIndex();
    skierRowFile.close();

    RawRecord rawRecord = new RawRecord();

    rawFile.seek(row1 * RawRecord.SIZE);
    for (int i = row1; i < row2; i++) {
      rawRecord.readFromFile(rawFile);
      stringBuilder.append(rawRecord.toString());
    }
    rawFile.close();
    return stringBuilder.toString();
  }

}
