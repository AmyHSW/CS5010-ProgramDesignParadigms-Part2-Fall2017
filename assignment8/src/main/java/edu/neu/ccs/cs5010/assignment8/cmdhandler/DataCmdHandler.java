package edu.neu.ccs.cs5010.assignment8.cmdhandler;

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
public class DataCmdHandler implements ICmdHandler {

  private final StringBuilder errorMessage = new StringBuilder();
  private String testFilename = "";
  private boolean valid = false;

  /**
   * Constructs a new CmdHandler with the input arguments.
   * @param args the command-line arguments
   */
  public DataCmdHandler(String[] args) {
    validate(args);
  }

  private void validate(String[] args) {
    int len = args.length;
    if (len != 1) {
      errorMessage.append(
              "Please provide one input arguments: test data filename.\n");
      return;
    }
    if (parseTestFile(args[0])) {
      valid = true;
    }
  }

  private boolean parseTestFile(String argument) {
    if (!argument.endsWith(".csv")) {
      errorMessage.append(argument).append(" is not a csv file.\n");
      return false;
    }
    testFilename = argument;
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
  public String getTestFilename() {
    return testFilename;
  }

  /**
   * Returns the error message.
   * @return the error message.
   */
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

    DataCmdHandler that = (DataCmdHandler) other;

    return testFilename.equals(that.testFilename);
  }

  @Override
  public int hashCode() {
    return testFilename.hashCode();
  }


}
