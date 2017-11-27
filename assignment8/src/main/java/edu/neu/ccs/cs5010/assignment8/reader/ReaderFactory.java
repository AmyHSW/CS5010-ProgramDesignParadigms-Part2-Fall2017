package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidQueryIdException;

import java.io.IOException;

public final class ReaderFactory {

  public static IReader getReader(int queryId,
                                  int parameter) throws IOException {
    switch (queryId) {
      case 1: return new SkiersReader(parameter);
      case 2: return new RawReader(parameter);
      case 3: return new HoursReader(parameter);
      case 4: return new LiftsReader(parameter);
      default: throw new InvalidQueryIdException(queryId + " is invalid!");
    }
  }
}
