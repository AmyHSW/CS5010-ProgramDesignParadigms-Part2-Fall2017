package edu.neu.ccs.cs5010.assignment9;

/**
 * The CmdHandler class handles the input command-line arguments.
 *
 * <p>The required arguments are the test data filename to process.
 *
 * <p>If the arguments are not in valid format, an error message will be accumulated and
 * a usage message will be attached to end of the error message.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class CmdHandler implements ICmdHandler {

  private final StringBuilder errorMessage = new StringBuilder();
  private String hostname = "";
  private int portNumber = -1;
  private boolean valid = false;

  /**
   * Constructs a new CmdHandler with the input arguments.
   * @param args the command-line arguments
   */
  public CmdHandler(String[] args) {
    validate(args);
  }

  private void validate(String[] args) {
    int len = args.length;
    if (len != 2) {
      errorMessage.append(
              "Please provide two arguments: host name and port number.\n");
      return;
    }
    if (parseHostName(args[0]) && parsePortNumber(args[1])) {
      valid = true;
    }
  }

  private boolean parseHostName(String argument) {
    hostname = argument;
    return true;
  }

  private boolean parsePortNumber(String argument) {
    if (!argument.matches("\\d+")) {
      errorMessage.append("hostname ").append(argument).append(" is not an positive integer\n");
      return false;
    }
    portNumber = Integer.parseInt(argument);
    return true;
  }

  /**
   * Returns if the arguments are in valid format.
   * @return true if the arguments are valid, false otherwise.
   */
  @Override
  public boolean isValid() {
    return valid;
  }

  /**
   * Returns the test file name.
   * @return the test file name.
   */
  @Override
  public String getHostname() {
    return hostname;
  }

  @Override
  public int getPortNumber() {
    return portNumber;
  }

  /**
   * Returns the error message.
   * @return the error message.
   */
  @Override
  public String getErrorMessage() {
    return errorMessage.toString();
  }




}
