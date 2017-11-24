package edu.neu.ccs.cs5010.assignment8.processors;

import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputDataException;
import edu.neu.ccs.cs5010.assignment8.hour.Hour;
import edu.neu.ccs.cs5010.assignment8.lift.ILift;
import edu.neu.ccs.cs5010.assignment8.lift.Lift;
import edu.neu.ccs.cs5010.assignment8.skier.ISkier;
import edu.neu.ccs.cs5010.assignment8.skier.Skier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The SequentialProcessor represents a concrete sequential processor.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SequentialProcessor implements IProcessor {

  private static final int SKIER_INDEX = 2;
  private static final int LIFT_INDEX = 3;
  private static final int TIME_INDEX = 4;

  private final List<String[]> inputData;
  private final List<ILift> liftList;
  private final List<List<ILift>> hourRides;
  private Duration runTime;

  private final Map<String, ISkier> skierMap;

  /**
   * The constructor of SequentialProcessor.
   *
   * @param  inputData the list of string array parsing from the csv file
   * @throws InvalidInputDataException when given data doesn't contain enough information
   */
  public SequentialProcessor(List<String[]> inputData) {
    if (inputData == null || inputData.size() <= 1) {
      throw new InvalidInputDataException("Input data doesn't contain enough information.");
    }
    this.inputData = inputData.subList(1, inputData.size());
    liftList = new ArrayList<>();
    hourRides = new ArrayList<>();
    initHourRides();
    initLiftList();
    skierMap = new HashMap<>();
  }

  private void initLiftList() {
    for (int i = 0; i < Lift.LIFT_NUM; i++) {
      liftList.add(new Lift(i));
    }
  }

  private void initHourRides() {
    for (int i = 0; i < Hour.HOUR_NUM; i++) {
      List<ILift> tempList = new ArrayList<>();
      for (int j = 0; j < Lift.LIFT_NUM; j++) {
        tempList.add(new Lift(j));
      }
      hourRides.add(tempList);
    }
  }

  @Override
  public void processInput() {
    long startTime = System.currentTimeMillis();
    for (String[] record : inputData) {
      processSkier(record[SKIER_INDEX], record[LIFT_INDEX]);
      processLift(record[LIFT_INDEX]);
      processHour(Hour.toIndex(record[TIME_INDEX]), Lift.toIndex(record[LIFT_INDEX]));
    }
    runTime = Duration.ofMillis(System.currentTimeMillis() - startTime);
  }

  private void processSkier(String skierId, String liftId) {
    if (!skierMap.containsKey(skierId)) {
      skierMap.put(skierId, new Skier(skierId));
    }
    ISkier skier = skierMap.get(skierId);
    skier.incrementNumRides();
    skier.increaseVerticalMeters(Lift.toVerticalMeters(liftId));
  }

  private void processLift(String liftId) {
    liftList.get(Lift.toIndex(liftId)).incrementNumber();
  }

  private void processHour(int hourIndex, int liftIndex) {
    hourRides.get(hourIndex).get(liftIndex).incrementNumber();
  }

  @Override
  public Map<String, ISkier> getSkierMap() {
    return skierMap;
  }

  @Override
  public List<ILift> getLiftList() {
    return liftList;
  }

  @Override
  public List<List<ILift>> getHourRides() {
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
