package edu.neu.ccs.cs5010.assignment8.Database;

import edu.neu.ccs.cs5010.assignment8.Record.HourRecord;
import edu.neu.ccs.cs5010.assignment8.Record.IRecord;

import java.io.IOException;

public class HourDatabase extends Database {

  public HourDatabase(String fileString) throws IOException {
    super(fileString);
  }
/*
  @Override
  public synchronized IRecord getRecord(int id) throws IOException {
    IRecord record = new HourRecord();
    if (id < 1) {
      throw new InvalidInputArgumentException("invalid ID!!");
    }
    file.seek((id - 1) * HourRecord.SIZE);
    record.readFromFile(file);
    return record;
  }*/

  @Override
  public void addRecord(IRecord record) throws IOException {
    file.seek((record.getParameter() - 1) * HourRecord.SIZE);
    record.writeToFile(file);
  }
}
