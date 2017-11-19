package edu.neu.ccs.cs5010.processors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequentialProcessor implements IProcessor {

  private final List<String[]> inputData;
  private Map<String, Integer> skierNumRides;
  private Map<String, Integer> skierVerticalMeters;
  private Map<String, Integer> liftNumRides;
  private Map<String, Map<String, Integer>> hourRides;
  private static final int SKIER_INDEX = 2;
  private static final int LIFT_INDEX = 3;
  private static final int TIME_INDEX = 4;
  private static final int MINUTES_IN_HOUR = 60;
  private static final String LIFT_LEVEL1 = "10";
  private static final String LIFT_LEVEL2 = "20";
  private static final String LIFT_LEVEL3 = "30";
  private static final int LIFT_LEVEL1_HEIGHT = 200;
  private static final int LIFT_LEVEL2_HEIGHT = 300;
  private static final int LIFT_LEVEL3_HEIGHT = 400;
  private static final int LIFT_LEVEL4_HEIGHT = 500;

  public SequentialProcessor(List<String[]> inputData) {
    this.inputData = inputData.subList(1, inputData.size());
    skierNumRides = new HashMap<>();
    skierVerticalMeters = new HashMap<>();
    liftNumRides = new HashMap<>();
    hourRides = new HashMap<>();
  }

  @Override
  public void processInput() {
    long startTime = System.currentTimeMillis();
    for (String[] record : inputData) {
      process(record);
    }
    System.out.println("sequential runs " + (System.currentTimeMillis() - startTime));
  }

  private void process(String[] record) {
    String skier = record[SKIER_INDEX];
    String lift = record[LIFT_INDEX];
    String hour = Integer.toString(
        (Integer.parseInt(record[TIME_INDEX]) - 1) / MINUTES_IN_HOUR + 1);
    processSkier(skier, lift);
    processLift(lift);
    processHour(hour,lift);

  }

  private void processSkier(String skier, String lift) {
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

  private void processLift(String lift) {
    liftNumRides.put(lift, liftNumRides.getOrDefault(lift, 0) + 1);
  }

  private void processHour(String hour, String lift) {
    if (!hourRides.containsKey(hour)) {
      hourRides.put(hour, new HashMap<>());
    }
    hourRides.get(hour).put(lift, hourRides.get(hour).getOrDefault(lift, 0) + 1);
  }

  @Override
  public Map<String, Integer> getSkierVerticalMeters() {
    return skierVerticalMeters;
  }

  @Override
  public Map<String, Integer> getLiftNumRides() {
    return liftNumRides;
  }

  @Override
  public Map<String, Map<String, Integer>> getHourRides() {
    return hourRides;
  }
}
