package edu.neu.ccs.cs5010.assignment8.Database;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.Record.SkierRecord;
import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputArgumentException;

import java.io.IOException;

public class SkierDatabase extends Database {

  public SkierDatabase(String fileString) throws IOException {
    super(fileString);
  }

  public IRecord getRecord(int id) throws IOException {
    if (id < 1) {
      throw new InvalidInputArgumentException("invalid ID!!");
    }
    IRecord record = new SkierRecord();
    file.seek((id - 1) * SkierRecord.SIZE);
    record.readFromFile(file);
    return record;
  }

  public void insertRecord(IRecord record) throws IOException {
    if (getRecord(record.getParameter()).getParameter() != 0) {
      throw new InvalidInputArgumentException("Cannot add new. Record already exists.");
    }
    file.seek((record.getParameter() - 1) * SkierRecord.SIZE);
    record.writeToFile(file);
  }

  public void updateNumberOfViews(SkierRecord record)
          throws IllegalArgumentException, IOException {
    if (record.getParameter() == 0) {
      throw new InvalidInputArgumentException("Cannot update. Record does not exist.");
    }
    record.updateNumberOfViewsToFile(file);
  }

}
