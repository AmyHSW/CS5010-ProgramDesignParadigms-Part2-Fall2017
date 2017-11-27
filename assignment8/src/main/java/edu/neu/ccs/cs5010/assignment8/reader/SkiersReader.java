package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.Record.SkierRecord;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SkiersReader implements IReader {

  private final int parameter;
  private final RandomAccessFile file;
  private final ReentrantReadWriteLock readWriteLock =
          new ReentrantReadWriteLock();

  public SkiersReader(int parameter) throws IOException {
    this.parameter = parameter;
    file = new RandomAccessFile("skiers.dat", "rw");
  }

  @Override
  public String read() throws IOException {
    IRecord record = new SkierRecord();
    readWriteLock.writeLock().lock();
    try {
      file.seek((parameter - 1) * SkierRecord.SIZE);
      record.readFromFile(file);
      ((SkierRecord)record).updateNumberOfViewsToFile(file);
    } finally {
      readWriteLock.writeLock().unlock();
    }
    file.close();
    return record.toString();
  }
}
