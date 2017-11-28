package edu.neu.ccs.cs5010.assignment8.cmdhandler;

/**
 * The CmdHandler class handles the input command-line arguments.
 *
 * <p>The required arguments are the test data filename and number of queries to process.
 * If 20 is not a factor of the number of queries, then the input arguments are invalid.
 *
 * <p>If the arguments are not in valid format, an error message will be accumulated and
 * a usage message will be attached to end of the error message.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class QueryCmdHandler implements IQueryCmdHandler {

  private static final int NUM_THREADS = 20;
  private final StringBuilder errorMessage = new StringBuilder();
  private String testFilename = "";
  private int numQueries = 0;
  private boolean valid = false;

  /**
   * Constructs a new CmdHandler with the input arguments.
   * @param args the command-line arguments
   */
  public QueryCmdHandler(String[] args) {
    validate(args);
  }

  private void validate(String[] args) {
    int len = args.length;
    if (len != 2) {
      errorMessage.append(
          "Please provide two input arguments: test data filename and number of queries.\n");
      return;
    }
    if (parseTestFile(args[0]) && parseNumQueries(args[1])) {
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

  private boolean parseNumQueries(String argument) {
    if (!argument.matches("\\d+")) {
      errorMessage.append(argument).append(" is not an positive integer\n");
      return false;
    }
    int number = Integer.parseInt(argument);
    if (validateNumQueries(number)) {
      numQueries = number;
      return true;
    } else {
      errorMessage.append(NUM_THREADS)
          .append(" is not a factor of the number of queries: ")
          .append(number);
      return false;
    }
  }

  private boolean validateNumQueries(int number) {
    return number % NUM_THREADS == 0;
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
   * Returns the number of queries.
   * @return the number of queries.
   */
  @Override
  public int getNumQueries() {
    return numQueries;
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

    QueryCmdHandler that = (QueryCmdHandler) other;

    if (numQueries != that.numQueries) {
      return false;
    }
    return testFilename.equals(that.testFilename);
  }

  @Override
  public int hashCode() {
    int result = testFilename.hashCode();
    result = 31 * result + numQueries;
    return result;
  }
}
