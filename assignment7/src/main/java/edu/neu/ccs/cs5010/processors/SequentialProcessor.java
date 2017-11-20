package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.lift.Hour;
import edu.neu.ccs.cs5010.lift.Lift;
import edu.neu.ccs.cs5010.skier.Skier;
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
  private final Map<String, Skier> skierMap;
  private final List<Lift> liftList;
  private final List<Map<String, Integer>> hourRides;

  private Duration runTime;

  public SequentialProcessor(List<String[]> inputData) {
    validate(inputData);

    this.inputData = inputData.subList(1, inputData.size());
    skierMap = new HashMap<>();
    liftList = new ArrayList<>();
    hourRides = new ArrayList<>();
    initLiftList();
    initHourRides();
  }

  private void validate(List<String[]> input) {
    if (input == null || input.size() <= 1) {
      throw new InvalidInputDataException("Input data doesn't contain enough information.");
    }
  }

  private void initLiftList() {
    for (int i = 0; i <= 40; i++) {
      liftList.add(new Lift(Integer.toString(i)));
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

  private void processSkier(String skierId, String liftId) {
    if (!skierMap.containsKey(skierId)) {
      skierMap.put(skierId, new Skier(skierId));
    }
    Skier skier = skierMap.get(skierId);
    skier.incrementNumRides();
    skier.increaseVerticalMeters(Lift.toVerticalMeters(liftId));
  }

  private void processLift(String liftId) {
    liftList.get(Integer.parseInt(liftId)).incrementNumber();
  }

  private void processHour(int hourIndex, String lift) {
    hourRides.get(hourIndex).put(lift, hourRides.get(hourIndex).getOrDefault(lift, 0) + 1);
  }

  @Override
  public Map<String, Skier> getSkierMap() {
    return skierMap;
  }

  @Override
  public List<Lift> getLiftList() {
    // removes the element at index-0
    return liftList.subList(1, liftList.size());
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
