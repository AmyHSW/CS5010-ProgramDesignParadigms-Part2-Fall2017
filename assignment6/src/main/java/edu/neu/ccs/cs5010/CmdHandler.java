package edu.neu.ccs.cs5010;

public class CmdHandler implements ICmdHandler {

  private static final int NUM_RECOMMENDATIONS_LOWER_BOUND = 1;
  private static final int NUM_RECOMMENDATIONS_UPPER_BOUND = 100;
  private static final int NUM_RECOMMENDATIONS_DEFAULT = 15;

  private static final int TOTAL_NUM_USERS_SMALL = 100;
  private static final int TOTAL_NUM_USERS_LARGE = 9500;
  private static final int NUM_USERS_TO_PROCESS_LOWER_BOUND = 1;
  private static final int NUM_USERS_TO_PROCESS_DEFAULT = 100;

  private static final char PROCESSING_FLAG_DEFAULT = 's';

  private static final String PROCESSING_FLAG = "--processing-flag";
  private static final String NUM_USERS_TO_PROCESS = "--number-of-users-to-process";
  private static final String NUM_RECOMMENDATIONS = "--number-of-recommendations";

  private boolean valid = false;
  private StringBuilder errorMessage = new StringBuilder();
  private String nodeFile = "";
  private String edgeFile = "";
  private String outputFile = "";
  private char processingFlag = PROCESSING_FLAG_DEFAULT;
  private int numUsersToProcess = NUM_USERS_TO_PROCESS_DEFAULT;
  private int numRecommendations = NUM_RECOMMENDATIONS_DEFAULT;

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
    if (validateOptionalArguments(args, 3, len - 1)) {
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

  private boolean validateOptionalArguments(String[] args, int start, int end) {
    if (start == args.length) {
      return true;
    }
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
        if (argument.length() > 1) {
          return false;
        }
        processingFlag = argument.charAt(0);
        return true;
      case NUM_USERS_TO_PROCESS:
        return isInteger(argument) && validateNumUsersToProcess(Integer.parseInt(argument));
      case NUM_RECOMMENDATIONS:
        return isInteger(argument) && validateNumRecommendations(Integer.parseInt(argument));
      default:
        errorMessage.append(flag).append(" cannot be recognized\n");
        return false;
    }
  }

  private boolean isInteger(String argument) {
    if (argument.matches("\\d+")) {
      return true;
    } else {
      errorMessage.append(argument).append(" is not an integer\n");
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
      case "nodes_small.csv":
        if (num > TOTAL_NUM_USERS_SMALL) {
          errorMessage.append(num)
              .append(" is greater than total number of users\n");
          return false;
        }
        numUsersToProcess = num;
        return true;
      case "nodes_1000.csv":
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

  @Override
  public String getNodeFile() {
    return nodeFile;
  }

  @Override
  public String getEdgeFile() {
    return edgeFile;
  }

  @Override
  public String getOutputFile() {
    return outputFile;
  }

  @Override
  public char getProcessingFlag() {
    return processingFlag;
  }

  @Override
  public int getNumUsersToProcess() {
    return numUsersToProcess;
  }

  @Override
  public int getNumRecommendations() {
    return numRecommendations;
  }

  @Override
  public boolean isValid() {
    return valid;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage.toString();
  }

}
