package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.Hour;
import edu.neu.ccs.cs5010.Lift;
import edu.neu.ccs.cs5010.exceptions.InvalidInputDataException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequentialProcessor implements IProcessor {

  private static final int SKIER_INDEX = 2;
  private static final int LIFT_INDEX = 3;
  private static final int TIME_INDEX = 4;

  private final List<String[]> inputData;
  private final Map<String, Integer> skierNumRides;
  private final Map<String, Integer> skierVerticalMeters;
  private final Map<String, Integer> liftNumRides;
  private final List<Map<String, Integer>> hourRides;

  private Duration runTime;

  public SequentialProcessor(List<String[]> inputData) {
    validate(inputData);

    this.inputData = inputData.subList(1, inputData.size());
    skierNumRides = new HashMap<>();
    skierVerticalMeters = new HashMap<>();
    liftNumRides = new HashMap<>();
    hourRides = new ArrayList<>();
    initHourRides();
  }

  private void validate(List<String[]> input) {
    if (input == null || input.size() <= 1) {
      throw new InvalidInputDataException("Input data doesn't contain enough information.");
    }
  }

  private void initHourRides() {
    for (int i = 0; i < Hour.HOUR_NUM; i++) {
      hourRides.add(new HashMap<>());
    }
  }

  @Override
  public void processInput() {
    long startTime = System.currentTimeMillis();
    for (String[] record : inputData) {
      processSkier(record[SKIER_INDEX], record[LIFT_INDEX]);
      processLift(record[LIFT_INDEX]);
      processHour(Hour.toIndex(record[TIME_INDEX]), record[LIFT_INDEX]);
    }
    runTime = Duration.ofMillis(System.currentTimeMillis() - startTime);
  }

  private void processSkier(String skier, String lift) {
    skierNumRides.put(skier, skierNumRides.getOrDefault(skier, 0) + 1);
    int verticalMeters = Lift.toVerticalMeters(lift);
    skierVerticalMeters.put(skier, skierVerticalMeters.getOrDefault(skier, 0) + verticalMeters);
  }

  private void processLift(String lift) {
    liftNumRides.put(lift, liftNumRides.getOrDefault(lift, 0) + 1);
  }

  private void processHour(int hourIndex, String lift) {
    hourRides.get(hourIndex).put(lift, hourRides.get(hourIndex).getOrDefault(lift, 0) + 1);
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
  public List<Map<String, Integer>> getHourRides() {
    return hourRides;
  }

  @Override
  public Duration getRunTime() {
    return runTime;
  }

  @Override
  public String toString() {
    return "Sequential processor";
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    SequentialProcessor that = (SequentialProcessor) other;

    return inputData.equals(that.inputData);
  }

  @Override
  public int hashCode() {
    return inputData.hashCode();
  }
}
