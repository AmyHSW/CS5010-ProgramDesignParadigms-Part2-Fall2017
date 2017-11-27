package edu.neu.ccs.cs5010.assignment8.exception;

/**
 * Thrown to indicate that the lift id is invalid.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class InvalidLiftIdException extends RuntimeException {

  /**
   * Constructs a new InvalidLiftIdException with the error message.
   * @param message the detailed error message.
   */
  public InvalidLiftIdException(String message) {
    super(message);
  }
}
