package edu.neu.ccs.cs5010.assignment8.exception;

/**
 * Thrown to indicate that there is no more queries to generate.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class NoMoreQueriesException extends RuntimeException {

  /**
   * Constructs a new NoMoreQueriesException with the error message.
   * @param message the detailed error message.
   */
  public NoMoreQueriesException(String message) {
    super(message);
  }
}
