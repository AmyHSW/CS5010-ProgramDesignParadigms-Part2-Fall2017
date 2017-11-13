package edu.neu.ccs.cs5010;

/**
 * The ICmdHandler interface represents a cmd handler.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface ICmdHandler {

  /**
   * Returns the nodes csv filename.
   * @return the nodes csv filename.
   */
  String getNodeFile();

  /**
   * Returns the edges csv filename.
   * @return the edges csv filename.
   */
  String getEdgeFile();

  /**
   * Returns the output csv filename.
   * @return the output csv filename.
   */
  String getOutputFile();

  /**
   * Returns a character that represents the processing flag.
   * @return a character that represents the processing flag.
   */
  char getProcessingFlag();

  /**
   * Returns the number of users to process.
   * @return the number of users to process.
   */
  int getNumUsersToProcess();

  /**
   * Returns the number of recommendations.
   * @return the number of recommendations.
   */
  int getNumRecommendations();

  /**
   * Returns the lower bound to be an influencer.
   * @return the lower bound to be an influencer.
   */
  int getInfluencerBound();

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
