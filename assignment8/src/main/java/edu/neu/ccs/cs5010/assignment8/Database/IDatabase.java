package edu.neu.ccs.cs5010.assignment8.Database;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;

import java.io.IOException;

public interface IDatabase {

  void close() throws IOException;
  void addRecord(IRecord record) throws IOException;
}
