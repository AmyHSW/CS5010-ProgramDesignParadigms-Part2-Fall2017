package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.Record.RawRecord;
import edu.neu.ccs.cs5010.assignment8.Record.SkierRowRecord;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RawReader implements IReader {

  private final int parameter;
  private final RandomAccessFile file1;
  private final RandomAccessFile file2;

  public RawReader(int parameter) throws IOException {
    this.parameter = parameter;
    file1 = new RandomAccessFile("skierRow.dat", "r");
    file2 = new RandomAccessFile("liftRides.dat", "r");
  }

  @Override
  public String read() throws IOException {
    SkierRowRecord skierRowRecord = new SkierRowRecord();
    StringBuilder stringBuilder = new StringBuilder();
    file1.seek((parameter - 1) * SkierRowRecord.SIZE);
    skierRowRecord.readFromFile(file1);
    int row1 = skierRowRecord.getRowIndex();
    stringBuilder.append(skierRowRecord.getParameter()).append(":");
    skierRowRecord.readFromFile(file1);
    int row2 = skierRowRecord.getRowIndex();
    file1.close();

    RawRecord rawRecord = new RawRecord();

    file2.seek(row1 * RawRecord.SIZE);
    for (int i = row1; i < row2; i++) {
      rawRecord.readFromFile(file2);
      stringBuilder.append(rawRecord.toString());
    }
    file2.close();
    return stringBuilder.toString();
  }

}
