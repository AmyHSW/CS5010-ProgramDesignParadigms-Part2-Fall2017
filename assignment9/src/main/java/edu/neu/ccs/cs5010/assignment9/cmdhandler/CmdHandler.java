package edu.neu.ccs.cs5010.assignment9.cmdhandler;

/**
 * The CmdHandler class handles the input command-line arguments.
 *
 * <p>The required arguments are the hostname and port number.
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
    hostname = args[0];
    valid = parsePortNumber(args[1]);
  }

  private boolean parsePortNumber(String argument) {
    if (!argument.matches("\\d+")) {
      errorMessage.append("hostname ").append(argument).append(" is not an positive integer\n");
      return false;
    }
    portNumber = Integer.parseInt(argument);
    return true;
  }

  @Override
  public boolean isValid() {
    return valid;
  }

  @Override
  public String getHostname() {
    return hostname;
  }

  @Override
  public int getPortNumber() {
    return portNumber;
  }
  
  @Override
  public String getErrorMessage() {
    return errorMessage.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    CmdHandler that = (CmdHandler) other;

    if (portNumber != that.portNumber) {
      return false;
    }
    return hostname.equals(that.hostname);
  }

  @Override
  public int hashCode() {
    return 31 * hostname.hashCode() + portNumber;
  }
}
