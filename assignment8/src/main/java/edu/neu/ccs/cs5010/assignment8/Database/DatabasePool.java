package edu.neu.ccs.cs5010.assignment8.Database;

import edu.neu.ccs.cs5010.assignment8.Record.IRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabasePool {

  private final List<Database> databaseList;

  public DatabasePool() {
    databaseList = new ArrayList<>();
  }

  public void addDatabase(Database database) {
    databaseList.add(database);
  }

  public IRecord read(int queryId, int parameter) throws IOException {
    return databaseList.get(queryId - 1).getRecord(parameter);
  }
}
