package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.exceptions.InvalidInputDataException;
import edu.neu.ccs.cs5010.lift.Hour;
import edu.neu.ccs.cs5010.lift.Lift;
import edu.neu.ccs.cs5010.skier.ISkier;
import edu.neu.ccs.cs5010.skier.Skier;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The SequentialProcessor represents a concrete sequential processor.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SequentialProcessor extends Processor {

  private static final int SKIER_INDEX = 2;
  private static final int LIFT_INDEX = 3;
  private static final int TIME_INDEX = 4;

  private final Map<String, ISkier> skierMap;

  /**
   * The constructor of SequentialProcessor.
   *
   * @param  inputData the list of string array parsing from the csv file
   * @throws InvalidInputDataException when given data doesn't contain enough information
   */
  public SequentialProcessor(List<String[]> inputData) {
    super(inputData);
    skierMap = new HashMap<>();
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
