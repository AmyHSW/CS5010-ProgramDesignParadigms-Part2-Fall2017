package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.Record.LiftRecord;

import java.io.IOException;
import java.io.RandomAccessFile;

public class LiftsReader implements IReader {

  private RandomAccessFile file;
  private final int parameter;

  public LiftsReader(int parameter) throws IOException {
    this.parameter = parameter;
    file = new RandomAccessFile("lifts.dat", "r");
  }

  @Override
  public String read() throws IOException {
    IRecord record = new LiftRecord();
    file.seek((parameter - 1) * LiftRecord.SIZE);
    record.readFromFile(file);
    return record.toString();
  }
}
