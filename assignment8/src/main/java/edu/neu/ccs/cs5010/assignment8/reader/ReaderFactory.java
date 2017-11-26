package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.Database.Database;
import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidQueryIdException;

import java.io.BufferedWriter;

public class ReaderFactory {

  public static IReader getReader(int queryId,
                                  Database database,
                                  int parameter,
                                  BufferedWriter bufferedWriter) {
    switch (queryId) {
      case 1: return new SkiersReader(database, parameter, bufferedWriter);
      case 2: return new RawReader(database, parameter, bufferedWriter);
      case 3: return new HoursReader(database, parameter, bufferedWriter);
      case 4: return new LiftsReader(database, parameter, bufferedWriter);
      default: throw new InvalidQueryIdException(queryId + " is invalid!");
    }
  }
}
