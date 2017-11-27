package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.record.IRecord;
import edu.neu.ccs.cs5010.assignment8.record.SkierRecord;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The SkiersReader class reads the hours dat file to find the result of a query.
 * The RandomAccessFile is used to find the position of the record, so that the dat
 * file can be read in O(1) time.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SkiersReader implements IReader {

  private static final String SKIER_DATA_FILE = "skiers.dat";
  private final int parameter;
  private final RandomAccessFile file;
  private final ReentrantReadWriteLock readWriteLock =
          new ReentrantReadWriteLock();

  /**
   * Constructs a new skiers reader with the parameter of query.
   * @param parameter the parameter of a query
   * @throws IOException if there is an I/O failure
   */
  public SkiersReader(int parameter) throws IOException {
    this.parameter = parameter;
    file = new RandomAccessFile(SKIER_DATA_FILE, "rw");
  }

  /**
   * Returns the result of query by reading the dat file.
   * @return the result of query.
   * @throws IOException if there is an I/O failure.
   */
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
