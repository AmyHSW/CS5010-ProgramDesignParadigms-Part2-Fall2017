package edu.neu.ccs.cs5010.result;

import edu.neu.ccs.cs5010.lift.Lift;
import edu.neu.ccs.cs5010.skier.Skier;

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
   * @return list of strings of the result that will be written to the csv file
   */
  List<String> getSkierOutput(Map<String, Skier> skierMap);

  /**
   * Gets all lifts' rides number results, analyzing based on given information.
   *
   * @return list of strings of the result that will be written to the csv file
   */
  List<String> getLiftOutput(List<Lift> liftList);

  /**
   * Gets all lifts' rides number in 6 separate hours results, analyzing based on given information.
   *
   * @param hourRides the list stores lifts' rides number in 6 separate hours
   * @return list of strings of the result that will be written to the csv file
   */
  List<String> getHourOutput(List<Map<String, Integer>> hourRides);
}
