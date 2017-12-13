package edu.neu.ccs.cs5010;

/**
 * The CmdHandler class handles the input command-line arguments.
 *
 * <p>The required arguments are three csv files, that must be provided in the order of
 * nodes file, edges file and output file.
 *
 * <p>There are three optional arguments: the processing flag, the number of users to process,
 * and the number of recommendations. If one or more of the optional arguments are missing,
 * they will be assigned with the corresponding default values.
 *
 * <p>If the arguments are not in valid format, an error message will be accumulated and
 * a usage message will be attached to end of the error message.
 *
 * @author
 * Shuwan Huang, Jingyu Shen
 */
public class CmdHandler implements ICmdHandler {

  private static final int NUM_RECOMMENDATIONS_LOWER_BOUND = 1;
  private static final int NUM_RECOMMENDATIONS_UPPER_BOUND = 100;
  private static final int NUM_RECOMMENDATIONS_DEFAULT = 15;

  private static final String NODE_FILE_SMALL = "nodes_small.csv";
  private static final String NODE_FILE_LARGE = "nodes_10000.csv";
  private static final int TOTAL_NUM_USERS_SMALL = 100;
  private static final int TOTAL_NUM_USERS_LARGE = 9500;

  private static final int NUM_USERS_TO_PROCESS_LOWER_BOUND = 1;
  private static final int NUM_USERS_TO_PROCESS_DEFAULT = 100;

  private static final char PROCESSING_FLAG_DEFAULT = 's';
  private static final char RANDOM_PROCESS = 'r';
  private static final char PROCESS_FROM_END = 'e';

  private static final int INFLUENCER_BOUND_SMALL = 25;
  private static final int INFLUENCER_BOUND_LARGE = 250;

  private static final String PROCESSING_FLAG = "--processing-flag";
  private static final String NUM_USERS_TO_PROCESS = "--number-of-users-to-process";
  private static final String NUM_RECOMMENDATIONS = "--number-of-recommendations";
  private static final String USAGE_MSG =
      "Usage: \n"
          + "Please provide 3 csv files in the order of nodes file, edges file and output file.\n"
          + "For optional input, please provide a flag in front of the input.\n"
          + "--processing-flag <char>           specifies the processing flag\n"
          + "--number-of-users-to-process <int>    specifies the number of users to process\n"
          + "--number-of-recommendations <int>  specifies the number of recommendations\n"
          + "For example:\n"
          + "nodes_small.csv edges_small.csv output.csv --processing-flag e\n"
          + "--number-of-users-to-process 50 --number-of-recommendations 10\n";
  private static final int START_INDEX_OF_OPTIONAL_ARGUMENTS = 3;

  private boolean valid = false;
  private StringBuilder errorMessage = new StringBuilder();
  private String nodeFile = "";
  private String edgeFile = "";
  private String outputFile = "";
  private char processingFlag = PROCESSING_FLAG_DEFAULT;
  private int numUsersToProcess = NUM_USERS_TO_PROCESS_DEFAULT;
  private int numRecommendations = NUM_RECOMMENDATIONS_DEFAULT;

  /**
   * Constructs a new CmdHandler with the input arguments.
   * @param args the command-line arguments
   */
  public CmdHandler(String[] args) {
    validate(args);
  }

  private void validate(String[] args) {
    int len = args.length;
    if (len < 3) {
      errorMessage.append("Some required csv file(s) are missing.\n");
      return;
    }
    if (!validateRequiredArguments(args[0], args[1], args[2])) {
      return;
    }
    if (validateOptionalArguments(args)) {
      valid = true;
    }
  }

  private boolean validateRequiredArguments(String nodeFile, String edgeFile, String outputFile) {
    if (!validateCsvFile(nodeFile)
        || !validateCsvFile(edgeFile)
        || !validateCsvFile(outputFile)) {
      return false;
    }
    this.nodeFile = nodeFile;
    this.edgeFile = edgeFile;
    this.outputFile = outputFile;
    return true;
  }

  private boolean validateCsvFile(String csvFile) {
    if (csvFile.endsWith(".csv")) {
      return true;
    } else {
      errorMessage.append(csvFile).append(" is not a csv file.\n");
      return false;
    }
  }

  private boolean validateOptionalArguments(String[] args) {
    int start = START_INDEX_OF_OPTIONAL_ARGUMENTS;
    if (start == args.length) {
      return true;
    }
    int end = args.length - 1;
    while (start < end) {
      if (!validateArgumentPair(args[start++], args[start++])) {
        return false;
      }
    }
    if (start == end) {
      errorMessage.append(args[start]).append(" is unsupported\n");
      return false;
    } else {
      return true;
    }
  }

  private boolean validateArgumentPair(String flag, String argument) {
    switch (flag) {
      case PROCESSING_FLAG:
        return validateProcessingFlag(argument);
      case NUM_USERS_TO_PROCESS:
        return isInteger(argument) && validateNumUsersToProcess(Integer.parseInt(argument));
      case NUM_RECOMMENDATIONS:
        return isInteger(argument) && validateNumRecommendations(Integer.parseInt(argument));
      default:
        errorMessage.append(flag).append(" cannot be recognized\n");
        return false;
    }
  }

  private boolean validateProcessingFlag(String arg) {
    if (arg.length() == 1
        && (arg.charAt(0) == PROCESSING_FLAG_DEFAULT
        || arg.charAt(0) == RANDOM_PROCESS
        || arg.charAt(0) == PROCESS_FROM_END)) {
      processingFlag = arg.charAt(0);
      return true;
    } else {
      errorMessage.append(arg).append(" is not a valid processing flag\n");
      return false;
    }
  }

  private boolean isInteger(String argument) {
    if (argument.matches("\\d+")) {
      return true;
    } else {
      errorMessage.append(argument).append(" is not an positive integer\n");
      return false;
    }
  }

  private boolean validateNumUsersToProcess(int num) {
    if (num < NUM_USERS_TO_PROCESS_LOWER_BOUND) {
      errorMessage.append(num)
          .append(" is less than lower bound of number of users to process\n");
      return false;
    }
    switch (nodeFile) {
      case NODE_FILE_SMALL:
        if (num > TOTAL_NUM_USERS_SMALL) {
          errorMessage.append(num)
              .append(" is greater than total number of users\n");
          return false;
        }
        numUsersToProcess = num;
        return true;
      case NODE_FILE_LARGE:
        if (num > TOTAL_NUM_USERS_LARGE) {
          errorMessage.append(num)
              .append(" is greater than total number of users\n");
          return false;
        }
        numUsersToProcess = num;
        return true;
      default:
        errorMessage.append(nodeFile).append(" is not supported\n");
        return false;
    }
  }

  private boolean validateNumRecommendations(int num) {
    if (num >= NUM_RECOMMENDATIONS_LOWER_BOUND && num <= NUM_RECOMMENDATIONS_UPPER_BOUND) {
      numRecommendations = num;
      return true;
    } else {
      errorMessage.append(num).append(" is not between ").append(NUM_RECOMMENDATIONS_LOWER_BOUND)
          .append(" and ").append(NUM_RECOMMENDATIONS_UPPER_BOUND);
      return false;
    }
  }

  /**
   * Returns the nodes csv filename.
   * @return the nodes csv filename.
   */
  @Override
  public String getNodeFile() {
    return nodeFile;
  }

  /**
   * Returns the edges csv filename.
   * @return the edges csv filename.
   */
  @Override
  public String getEdgeFile() {
    return edgeFile;
  }

  /**
   * Returns the output csv filename.
   * @return the output csv filename.
   */
  @Override
  public String getOutputFile() {
    return outputFile;
  }

  /**
   * Returns a character that represents the processing flag.
   * @return a character that represents the processing flag.
   */
  @Override
  public char getProcessingFlag() {
    return processingFlag;
  }

  /**
   * Returns the number of users to process.
   * @return the number of users to process.
   */
  @Override
  public int getNumUsersToProcess() {
    return numUsersToProcess;
  }

  /**
   * Returns the number of recommendations.
   * @return the number of recommendations.
   */
  @Override
  public int getNumRecommendations() {
    return numRecommendations;
  }

  /**
   * Returns the lower bound to be an influencer.
   * @return the lower bound to be an influencer.
   */
  @Override
  public int getInfluencerBound() {
    switch (nodeFile) {
      case NODE_FILE_SMALL:
        return INFLUENCER_BOUND_SMALL;
      case NODE_FILE_LARGE:
        return INFLUENCER_BOUND_LARGE;
      default:
        throw new InvalidNodeFileException(nodeFile
            + " should be nodes_small.csv or nodes_10000.csv");
    }
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
   * Returns the error message.
   * @return the error message.
   */
  @Override
  public String getErrorMessage() {
    if (errorMessage.length() > 0) {
      errorMessage.append(USAGE_MSG);
    }
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

    if (processingFlag != that.processingFlag) {
      return false;
    }
    if (numUsersToProcess != that.numUsersToProcess) {
      return false;
    }
    if (numRecommendations != that.numRecommendations) {
      return false;
    }
    if (!nodeFile.equals(that.nodeFile)) {
      return false;
    }
    if (!edgeFile.equals(that.edgeFile)) {
      return false;
    }
    return outputFile.equals(that.outputFile);
  }

  @Override
  public int hashCode() {
    int result = nodeFile.hashCode();
    result = 31 * result + edgeFile.hashCode();
    result = 31 * result + outputFile.hashCode();
    result = 31 * result + (int) processingFlag;
    result = 31 * result + numUsersToProcess;
    result = 31 * result + numRecommendations;
    return result;
  }
}
