package edu.neu.ccs.cs5010.assignment8.Database;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.Record.SkierRecord;
import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputArgumentException;

import java.io.IOException;

public class SkierDatabase extends Database {

  private int readers;

  public SkierDatabase(String fileString) throws IOException {
    super(fileString);
    this.readers = 0;
  }

  public synchronized IRecord getRecord(int id) throws IOException {
    if (id < 1) {
      throw new InvalidInputArgumentException("invalid ID!!");
    }
    IRecord record = new SkierRecord(id - 1);
    file.seek((id - 1) * SkierRecord.SIZE);
    record.readFromFile(file);
    updateNumberOfViews((SkierRecord) record);
    return record;
  }

  public void addRecord(IRecord record) throws IOException {
    file.seek((record.getParameter() - 1) * SkierRecord.SIZE);
    record.writeToFile(file);
  }

  public void updateNumberOfViews(SkierRecord record) throws IOException {
    if (record.getParameter() == 0) {
      throw new InvalidInputArgumentException("Cannot update. Record does not exist: "
          + record.getParameter());
    }
    record.updateNumberOfViewsToFile(file);
  }
}
