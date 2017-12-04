package edu.neu.ccs.cs5010.assignment9.cmdhandler;

/**
 * The ICmdHandler interface represents a cmd handler.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface ICmdHandler {

  /**
   * Returns the hostname.
   * @return the hostname.
   */
  String getHostname();

  /**
   * Returns the port number.
   * @return the port number.
   */
  int getPortNumber();

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
