package edu.neu.ccs.cs5010.assignment8.Database;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Database implements IDatabase {

  protected RandomAccessFile file;

  public Database(String fileString) throws IOException {
    file = new RandomAccessFile(fileString, "rw");
  }

  @Override
  public void close() throws IOException {
    if (file != null)
      file.close();
  }

  @Override
  public void addRecord(IRecord record) throws IOException {
    record.writeToFile(file);
  }

}
