package edu.neu.ccs.cs5010.assignment8.result;

import edu.neu.ccs.cs5010.assignment8.hour.Hour;
import edu.neu.ccs.cs5010.assignment8.lift.ILift;
import edu.neu.ccs.cs5010.assignment8.skier.ISkier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ResultAnalyser represents a concrete result analyser.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class ResultAnalyser implements IResultAnalyser {

  private static final int TEN = 10;
  private static final String SKIERS_HEADER = "SkierID,Number of Rides,Vertical";
  private static final String LIFTS_HEADER = "LiftID,Number of Rides";

  @Override
  public List<String> getSkierOutput(List<ISkier> skierList) {
    List<String> output = new ArrayList<>();
    output.add(SKIERS_HEADER);
    for (int i = 0; i < skierList.size(); i++) {
      ISkier skier = skierList.get(i);
      String line = skier.getSkierId() + "," + skier.getNumRides() + "," + skier.getVerticalMeters();
      output.add(line);
    }
    return output;
  }

  @Override
  public List<String> getLiftOutput(List<ILift> liftList) {
    List<String> output = new ArrayList<>();
    output.add(LIFTS_HEADER);
    for (ILift lift: liftList) {
      output.add(lift.getLiftId() + "," + lift.getNumber());
    }
    return output;
  }

  @Override
  public List<String> getHourOutput(List<List<ILift>> hourList) {
    List<String> output = new ArrayList<>();
    for (int i = 0; i < Hour.HOUR_TOTAL; i++) {
      List<ILift> liftsRides = hourList.get(i);
      Collections.sort(liftsRides);
      output.add("Hour " + (i + 1));
      output.add(LIFTS_HEADER);
      for (int j = 0; j < Math.min(TEN, liftsRides.size()); j++) {
        String line = liftsRides.get(j).getLiftId() + "," + liftsRides.get(j).getNumber();
        output.add(line);
      }
    }
    return output;
  }
}
