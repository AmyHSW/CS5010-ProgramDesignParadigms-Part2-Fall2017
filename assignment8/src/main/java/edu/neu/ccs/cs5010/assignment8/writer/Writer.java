package edu.neu.ccs.cs5010.assignment8.writer;

import edu.neu.ccs.cs5010.assignment8.Database.Database;
import edu.neu.ccs.cs5010.assignment8.Record.IRecord;

import java.io.IOException;
import java.util.List;

public class Writer {

  public static void writeToData(List<IRecord> list, Database database) throws IOException {
    for (IRecord record: list) {
      database.addRecord(record);
    }
  }
}
