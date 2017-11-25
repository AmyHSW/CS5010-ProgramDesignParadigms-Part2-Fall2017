package edu.neu.ccs.cs5010.assignment8.skier;

import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputArgumentException;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SkierDatabase {
  private RandomAccessFile file;

  public SkierDatabase(String fileString)
          throws IOException {
    file = new RandomAccessFile(fileString, "rw");
  }

  public void close() throws IOException {
    if (file != null)
      file.close();
  }

  public SkierRecord getRecord(int id) throws IOException {
    SkierRecord record = new SkierRecord();
    if (id < 1) {
      throw new InvalidInputArgumentException("invalid ID!!");
    }
    file.seek((id - 1) * SkierRecord.SIZE);
    record.readFromFile(file);
    return record;
  }

  public void insertRecord(SkierRecord record)
          throws IllegalArgumentException, IOException {
    if (getRecord(record.getSkierId()).getSkierId() != 0) {
      throw new InvalidInputArgumentException("Cannot add new. Record already exists.");
    }
    file.seek((record.getSkierId() - 1) * SkierRecord.SIZE);
    record.writeToFile(file);
  }

  public void updateNumberOfViews(SkierRecord record)
          throws IllegalArgumentException, IOException {
    if (record.getSkierId() == 0) {
      throw new InvalidInputArgumentException("Cannot update. Record does not exist.");
    }
    record.updateNumberOfViewsToFile(file);
  }

}
