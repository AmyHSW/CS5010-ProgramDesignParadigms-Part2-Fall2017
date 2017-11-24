package edu.neu.ccs.cs5010.assignment8.cmdHandler;

/**
 * The ICmdHandler interface represents a cmd handler.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface ICmdHandler {

  /**
   * Returns the test data filename.
   * @return the test data filename.
   */
  String getTestFilename();

  /**
   * Returns the number of queries.
   * @return the number of queries.
   */
  int getNumQueries();

  /**
   * Returns if the arguments are in valid format.
   * @return true if the arguments are valid, false otherwise.
   */
  boolean isValid();

  /**
   * Returns the error message.
   * @return the error message.
   */
  String getErrorMessage();
}
