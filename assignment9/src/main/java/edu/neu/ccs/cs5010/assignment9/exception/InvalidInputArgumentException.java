package edu.neu.ccs.cs5010.assignment9.exception;

/**
 * Thrown to indicate that the input argument is invalid.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class InvalidInputArgumentException extends RuntimeException {

  /**
   * Constructs a new InvalidInputArgumentException with the error message.
   * @param message the detailed error message.
   */
  public InvalidInputArgumentException(String message) {
    super(message);
  }
}