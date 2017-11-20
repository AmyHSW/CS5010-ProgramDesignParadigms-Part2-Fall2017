package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * The ResultAnalyser represents a concrete result analyser.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class ResultAnalyser implements IResultAnalyser {

  private static final int HUNDRED = 100;
  private static final int TEN = 10;
  private static final String SKIER_VERTICAL_HEADER = "SkierID,Vertical";
  private static final String LIFT_RIDES_HEADER = "LiftID,Number of Rides";

  @Override
  public List<String> getSkierOutput(Map<String, Integer> skierVerticalMeters) {
    List<Map.Entry<String, Integer>> entries = new ArrayList<>(skierVerticalMeters.entrySet());
    entries.sort((meters1, meters2) -> meters2.getValue().compareTo(meters1.getValue()));
    List<String> skierVerticalTotals = new ArrayList<>();
    skierVerticalTotals.add(SKIER_VERTICAL_HEADER);
    for (int i = 0; i < Math.min(HUNDRED, entries.size()); i++) {
      Map.Entry<String, Integer> verticalMeters = entries.get(i);
      String line = verticalMeters.getKey() + "," + verticalMeters.getValue();
      skierVerticalTotals.add(line);
    }
    return skierVerticalTotals;
  }

  @Override
  public List<String> getLiftOutput(Map<String, Integer> liftNumRides) {
    List<Map.Entry<String, Integer>> entries = new ArrayList<>(liftNumRides.entrySet());
    entries.sort(Comparator.comparingInt(lift -> Integer.parseInt(lift.getKey())));
    List<String> liftsRides = new ArrayList<>();
    liftsRides.add(LIFT_RIDES_HEADER);
    for (Map.Entry<String, Integer> entry: entries) {
      String line = entry.getKey() + "," + entry.getValue();
      liftsRides.add(line);
    }
    return liftsRides;
  }

  @Override
  public List<String> getHourOutput(List<Map<String, Integer>> hourRides) {
    List<String> hourLiftRides = new ArrayList<>();
    for (int i = 0; i < Hour.HOUR_NUM; i++) {
      List<Map.Entry<String, Integer>> liftsRides = new ArrayList<>(hourRides.get(i).entrySet());
      liftsRides.sort((rides1, rides2) -> rides2.getValue().compareTo(rides1.getValue()));
      hourLiftRides.add("Hour " + (i + 1));
      hourLiftRides.add(LIFT_RIDES_HEADER);
      for (int j = 0; j < Math.min(TEN, liftsRides.size()); j++) {
        String line = liftsRides.get(j).getKey() + "," + liftsRides.get(j).getValue();
        hourLiftRides.add(line);
      }
    }
    return hourLiftRides;
  }
}
