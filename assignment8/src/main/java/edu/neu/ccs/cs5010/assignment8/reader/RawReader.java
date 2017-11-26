package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.Record.RawRecord;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RawReader implements IReader {

  private final int parameter;
  private final RandomAccessFile file;

  public RawReader(int parameter) throws IOException {
    this.parameter = parameter;
    file = new RandomAccessFile("liftRides.dat", "r");
  }

  @Override
  public String read() throws IOException {
    IRecord record = new RawRecord();
    file.seek((parameter - 1) * RawRecord.SIZE);
    record.readFromFile(file);
    return record.toString();
  }

}
