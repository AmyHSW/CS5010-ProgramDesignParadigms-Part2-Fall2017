package edu.neu.ccs.cs5010.assignment8.Database;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;

import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class Database {

  protected RandomAccessFile file;

  public Database(String fileString) throws IOException {
    file = new RandomAccessFile(fileString, "rw");
  }

  public void close() throws IOException {
    if (file != null)
      file.close();
  }

  public abstract IRecord getRecord(int id) throws IOException;

  public abstract void addRecord(IRecord record) throws IOException;


}
