package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.Record.HourRecord;
import edu.neu.ccs.cs5010.assignment8.Record.IRecord;

import java.io.IOException;
import java.io.RandomAccessFile;

public class HoursReader implements IReader {

  private final int parameter;
  private final RandomAccessFile file;

  public HoursReader(int parameter)throws IOException {
    this.parameter = parameter;
    file = new RandomAccessFile("hours.dat", "r");
  }

  @Override
  public String read() throws IOException {
    IRecord record = new HourRecord();
    file.seek((parameter - 1) * HourRecord.SIZE);
    record.readFromFile(file);
    return record.toString();
  }
}
