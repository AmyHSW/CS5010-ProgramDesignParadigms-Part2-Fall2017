package edu.neu.ccs.cs5010.assignment8.result;

import edu.neu.ccs.cs5010.assignment8.lift.ILift;
import edu.neu.ccs.cs5010.assignment8.skier.ISkier;

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
   * @param  skierMap the map stores all skiers' information
   * @return list of strings of the result that will be written to the csv file
   */
  List<String> getSkierOutput(Map<String, ISkier> skierMap);

  /**
   * Gets all lifts' rides number results, analyzing based on given information.
   *
   * @param liftList the list stores all lifts' information
   * @return list of strings of the result that will be written to the csv file
   */
  List<String> getLiftOutput(List<ILift> liftList);

  /**
   * Gets all lifts' rides number in 6 separate hours results, analyzing based on given information.
   *
   * @param hourRides the list stores lifts' rides number in 6 separate hours
   * @return list of strings of the result that will be written to the csv file
   */
  List<String> getHourOutput(List<List<ILift>> hourRides);
}
