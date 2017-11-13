package edu.neu.ccs.cs5010;

/**
 * The IRecommendationSystem represents a recommendation system.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IRecommendationSystem {

  /**
   * Starts recommending friends for the selected users.
   */
  void startRecommendation();

  /**
   * Saves the recommendations results to the output file.
   */
  void outputResults();

  /**
   * Prints to console the top ten most frequently recommended node IDs.
   */
  void printTopTen();
}
