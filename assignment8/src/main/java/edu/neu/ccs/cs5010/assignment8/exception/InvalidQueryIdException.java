package edu.neu.ccs.cs5010.assignment8.exception;

/**
 * Thrown to indicate that the query id is invalid.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class InvalidQueryIdException extends RuntimeException {

  /**
   * Constructs a new InvalidQueryIdException with the error message.
   * @param message the detailed error message.
   */
  public InvalidQueryIdException(String message) {
    super(message);
  }
}
