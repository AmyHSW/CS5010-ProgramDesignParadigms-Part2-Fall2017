package edu.neu.ccs.cs5010;

import java.util.List;

/**
 * Represent a CSV file parser interface.
 */
public interface ICsvParser {
  /**
   * Checks if there existing next line in the csv file.
   *
   * @return true if there's next line.
   */
  boolean hasNextLine();

  /**
   * Gets the next line's information, separating each string and stores in a list.
   *
   * @return the next line's information in a list of String
   */
  List<String> getNextLine();
}
