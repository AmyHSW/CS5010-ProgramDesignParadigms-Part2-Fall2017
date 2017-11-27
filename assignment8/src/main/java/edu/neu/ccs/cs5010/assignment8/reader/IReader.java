package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.exception.InvalidQueryIdException;

import java.io.IOException;

/**
 * The IReader interface represents a reader.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IReader {

  /**
   * Returns the result of query by reading the dat file.
   * @return the result of query.
   * @throws IOException if there is an I/O failure.
   */
  String read() throws IOException;

  /**
   * Returns a new reader for reading a specific dat file.
   * @param queryId the query id
   * @param parameter the parameter of the query
   * @return a new reader
   * @throws IOException if there is an I/O failure
   */
  static IReader getReader(int queryId,
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
