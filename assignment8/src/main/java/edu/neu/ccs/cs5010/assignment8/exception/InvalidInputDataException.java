package edu.neu.ccs.cs5010.assignment8.exception;

/**
 * Thrown to indicate that the input data is invalid.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class InvalidInputDataException extends RuntimeException {

  /**
   * Constructs a new InvalidInputDataException with the error message.
   * @param message the detailed error message.
   */
  public InvalidInputDataException(String message) {
    super(message);
  }
}
