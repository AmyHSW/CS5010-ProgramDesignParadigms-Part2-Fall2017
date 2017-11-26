package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.Record.SkierRecord;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SkiersReader implements IReader {

  private final int parameter;
  private final RandomAccessFile file;

  public SkiersReader(int parameter) throws IOException {
    this.parameter = parameter;
    file = new RandomAccessFile("skiers.dat", "rw");
  }

  @Override
  public String read() throws IOException {
    IRecord record = new SkierRecord();
    file.seek((parameter - 1) * SkierRecord.SIZE);
    record.readFromFile(file);
    return record.toString();
  }
}
