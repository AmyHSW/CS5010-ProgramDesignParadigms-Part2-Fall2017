package edu.neu.ccs.cs5010.assignment8.database;

import edu.neu.ccs.cs5010.assignment8.record.IRecord;

import java.io.IOException;
import java.util.List;

public interface IDatabase {

  void close() throws IOException;
  void writeRecordsToFile(List<IRecord> recordList) throws IOException;
}
