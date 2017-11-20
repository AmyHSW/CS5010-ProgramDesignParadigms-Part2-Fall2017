package edu.neu.ccs.cs5010;

import java.util.List;
import java.util.Map;

/**
 * The IResultAnalyser represents a result analyser interface.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IResultAnalyser {
  /**
   * Gets all skiers' vertical meters' results, analyzing based on given information.
   *
   * @param skierVerticalMeters the map stores all skiers's vertical meters in one day
   * @return list of strings of the result that will be written to the csv file
   */
  List<String> getSkierOutput(Map<String, Integer> skierVerticalMeters);

  /**
   * Gets all lifts' rides number results, analyzing based on given information.
   *
   * @param liftNumRides the map stores all lifts's rides number in one day
   * @return list of strings of the result that will be written to the csv file
   */
  List<String> getLiftOutput(Map<String, Integer> liftNumRides);

  /**
   * Gets all lifts' rides number in 6 separate hours results, analyzing based on given information.
   *
   * @param hourRides the list stores lifts' rides number in 6 separate hours
   * @return list of strings of the result that will be written to the csv file
   */
  List<String> getHourOutput(List<Map<String, Integer>> hourRides);
}
