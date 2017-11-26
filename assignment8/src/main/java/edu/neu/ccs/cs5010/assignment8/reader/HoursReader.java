package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.Database.Database;
import edu.neu.ccs.cs5010.assignment8.Record.IRecord;

import java.io.BufferedWriter;
import java.io.IOException;

public class HoursReader implements IReader {

  private final Database database;
  private final int parameter;
  private final BufferedWriter bufferedWriter;

  public HoursReader(Database database, int parameter, BufferedWriter bufferedWriter) {
    this.database = database;
    this.parameter = parameter;
    this.bufferedWriter = bufferedWriter;
  }

  @Override
  public void run() {
    try {
      IRecord record = database.getRecord(parameter);
      System.out.println(record);
      bufferedWriter.write(record.toString());
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }
}
