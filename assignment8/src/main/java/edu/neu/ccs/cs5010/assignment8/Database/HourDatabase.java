package edu.neu.ccs.cs5010.assignment8.Database;

import edu.neu.ccs.cs5010.assignment8.Record.HourRecord;
import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputArgumentException;

import java.io.IOException;

public class HourDatabase extends Database {

  public HourDatabase(String fileString) throws IOException {
    super(fileString);
  }

  @Override
  public IRecord getRecord(int id) throws IOException {
    IRecord record = new HourRecord();
    if (id < 1) {
      throw new InvalidInputArgumentException("invalid ID!!");
    }
    file.seek((id - 1) * HourRecord.SIZE);
    record.readFromFile(file);
    return record;
  }

  @Override
  public void insertRecord(IRecord record) throws IOException {
    if (getRecord(record.getParameter()).getParameter() != 0) {
      throw new InvalidInputArgumentException("Cannot add new. Record already exists.");
    }
    file.seek((record.getParameter() - 1) * HourRecord.SIZE);
    record.writeToFile(file);
  }
}
