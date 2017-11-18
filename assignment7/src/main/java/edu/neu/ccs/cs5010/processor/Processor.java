package edu.neu.ccs.cs5010.processor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class Processor implements IProcessor {

  protected Map<String, Integer> skierNumRides;
  protected Map<String, Integer> skierVerticalMeters;
  protected Map<String, Integer> liftNumRides;
  protected Map<String, Map<String, Integer>> hourRides;
  protected static final int SKIER_INDEX = 2;
  protected static final int LIFT_INDEX = 3;
  protected static final int TIME_INDEX = 4;
  protected static final int MINUTES_IN_HOUR = 60;
  protected static final String LIFT_LEVEL1 = "10";
  protected static final String LIFT_LEVEL2 = "20";
  protected static final String LIFT_LEVEL3 = "30";
  protected static final int LIFT_LEVEL1_HEIGHT = 200;
  protected static final int LIFT_LEVEL2_HEIGHT = 300;
  protected static final int LIFT_LEVEL3_HEIGHT = 400;
  protected static final int LIFT_LEVEL4_HEIGHT = 500;

  @Override
  public abstract void processInput();

  protected void processSkier(String skier, String lift) {
    skierNumRides.put(skier, skierNumRides.getOrDefault(skier, 0) + 1);
    if (lift.compareTo(LIFT_LEVEL1) <= 0) {
      skierVerticalMeters.put(
              skier, skierVerticalMeters.getOrDefault(skier, 0) + LIFT_LEVEL1_HEIGHT);
    } else if (lift.compareTo(LIFT_LEVEL2) <= 0) {
      skierVerticalMeters.put(
              skier, skierVerticalMeters.getOrDefault(skier, 0) + LIFT_LEVEL2_HEIGHT);
    } else if (lift.compareTo(LIFT_LEVEL3) <= 0) {
      skierVerticalMeters.put(
              skier, skierVerticalMeters.getOrDefault(skier, 0) + LIFT_LEVEL3_HEIGHT);
    } else {
      skierVerticalMeters.put(
              skier, skierVerticalMeters.getOrDefault(skier, 0) + LIFT_LEVEL4_HEIGHT);
    }
  }

  protected void processLift(String lift) {
    liftNumRides.put(lift, liftNumRides.getOrDefault(lift, 0) + 1);
  }

  protected void processHour(String hour, String lift) {
    hourRides.get(hour).put(lift, hourRides.get(hour).getOrDefault(lift, 0) + 1);
  }

  @Override
  public List<String> getSkierOutput() {
    List<Map.Entry<String, Integer>> entries = new ArrayList<>(skierVerticalMeters.entrySet());
    entries.sort((meters1, meters2) -> meters2.getValue().compareTo(meters1.getValue()));
    List<String> skierVerticalTotals = new ArrayList<>();
    skierVerticalTotals.add("SkierID,Vertical");
    for (int i = 0; i < 100; i++) {
      Map.Entry<String, Integer> verticalMeters = entries.get(i);
      String line = verticalMeters.getKey() + "," + verticalMeters.getValue();
      skierVerticalTotals.add(line);
    }
    return skierVerticalTotals;
  }

  @Override
  public List<String> getLiftOutput() {
    List<Map.Entry<String, Integer>> entries = new ArrayList<>(liftNumRides.entrySet());
    entries.sort(Comparator.comparing(Map.Entry::getKey));
    List<String> liftsRides = new ArrayList<>();
    liftsRides.add("LiftID, Number of Rides");
    for (Map.Entry<String, Integer> entry: entries) {
      String line = entry.getKey() + "," + entry.getValue();
      liftsRides.add(line);
    }
    return liftsRides;
  }

  @Override
  public List<String> getHourOutput() {
    List<Map.Entry<String, Map<String, Integer>>> entries = new ArrayList<>(hourRides.entrySet());
    entries.sort(Comparator.comparing(Map.Entry::getKey));
    List<String> hourLiftRides = new ArrayList<>();
    String header = "Hour,Number of Rides";
    for (Map.Entry<String, Map<String, Integer>> entry: entries) {
      List<Map.Entry<String, Integer>> liftsRides = new ArrayList<>(entry.getValue().entrySet());
      liftsRides.sort((rides1, rides2) -> rides2.getValue().compareTo(rides1.getValue()));
      hourLiftRides.add(header);
      for (int i = 0; i < 10; i++) {
        String line = entry.getKey() + "," + liftsRides.get(i).getValue();
        hourLiftRides.add(line);
      }
    }
    return hourLiftRides;
  }
}
