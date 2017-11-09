package edu.neu.ccs.cs5010;

public class InvalidUserException extends RuntimeException {

  public InvalidUserException(String message) {
    super(message);
  }
}
