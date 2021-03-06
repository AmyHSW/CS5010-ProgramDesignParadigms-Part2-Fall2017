package edu.neu.ccs.cs5010.assignment8.dataprocessor;

import edu.neu.ccs.cs5010.assignment8.exception.InvalidInputDataException;
import edu.neu.ccs.cs5010.assignment8.record.HourRecord;
import edu.neu.ccs.cs5010.assignment8.record.IRecord;
import edu.neu.ccs.cs5010.assignment8.record.LiftRecord;
import edu.neu.ccs.cs5010.assignment8.record.RawRecord;
import edu.neu.ccs.cs5010.assignment8.record.SkierRecord;
import edu.neu.ccs.cs5010.assignment8.record.SkierRowRecord;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * The SequentialDataProcessor represents a concrete sequential data processor.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SequentialDataProcessor implements IDataProcessor {

  private static final int SKIER_INDEX = 2;
  private static final int LIFT_INDEX = 3;
  private static final int TIME_INDEX = 4;

  private final List<String[]> inputData;
  private final List<IRecord> rawList;
  private final List<IRecord> skierList;
  private final List<IRecord> liftList;
  private final List<List<IRecord>> hourLiftsList;
  private final List<IRecord> skierRowList;
  private Duration runTime;

  /**
   * The constructor of SequentialDataProcessor.
   *
   * @param  inputData the list of string array parsing from the csv file
   * @throws InvalidInputDataException when given data doesn't contain enough information
   */
  public SequentialDataProcessor(List<String[]> inputData) {
    if (inputData == null || inputData.size() <= 1) {
      throw new InvalidInputDataException("Input data doesn't contain enough information.");
    }
    this.inputData = inputData.subList(1, inputData.size());
    rawList = new ArrayList<>(this.inputData.size());
    skierList = new ArrayList<>(SkierRecord.SKIER_TOTAL);
    liftList = new ArrayList<>(LiftRecord.LIFT_TOTAL);
    hourLiftsList = new ArrayList<>(HourRecord.HOUR_TOTAL);
    skierRowList = new ArrayList<>(SkierRecord.SKIER_TOTAL);
    initSkierList();
    initLiftList();
    initHourRides();
  }

  private void initLiftList() {
    for (int i = 0; i < LiftRecord.LIFT_TOTAL; i++) {
      liftList.add(new LiftRecord(i + 1));
    }
  }

  private void initHourRides() {
    for (int i = 0; i < HourRecord.HOUR_TOTAL; i++) {
      List<IRecord> tempList = new ArrayList<>();
      for (int j = 0; j < LiftRecord.LIFT_TOTAL; j++) {
        tempList.add(new LiftRecord(j + 1));
      }
      hourLiftsList.add(tempList);
    }
  }

  private void initSkierList() {
    for (int i = 0; i < SkierRecord.SKIER_TOTAL; i++) {
      skierList.add(new SkierRecord(i + 1));
    }
  }

  /**
   * Processes input and stores all information .
   */
  @Override
  public void processInput() {
    long startTime = System.currentTimeMillis();
    for (String[] record : inputData) {
      int skierId = Integer.parseInt(record[SKIER_INDEX]);
      int liftId = Integer.parseInt(record[LIFT_INDEX]);
      int time = Integer.parseInt(record[TIME_INDEX]);
      processRaw(skierId, time, liftId);
      processSkier(skierId, liftId);
      processLift(liftId);
      processHour(time, liftId);
    }
    processSkierRowIndex();
    runTime = Duration.ofMillis(System.currentTimeMillis() - startTime);
  }

  private void processRaw(int skierId, int time, int liftId) {
    IRecord raw = new RawRecord(skierId, time, liftId);
    rawList.add(raw);
  }

  private void processSkier(int skierId, int liftId) {
    SkierRecord skier = (SkierRecord) skierList.get(skierId - 1);
    skier.incrementNumRides();
    skier.increaseTotalVertical(LiftRecord.toVerticalMeters(liftId));
  }

  private void processLift(int liftId) {
    LiftRecord lift = (LiftRecord) liftList.get(liftId - 1);
    lift.incrementNumber();
  }

  private void processHour(int time, int liftId) {
    LiftRecord lift = (LiftRecord) hourLiftsList.get(HourRecord.toIndex(time)).get(liftId - 1);
    lift.incrementNumber();
  }

  private void processSkierRowIndex() {
    rawList.sort((record1, record2) -> {
      if (record1.getParameter() != record2.getParameter()) {
        return record1.getParameter() - record2.getParameter();
      } else {
        return ((RawRecord)record1).getTime() - ((RawRecord)record2).getTime();
      }
    });
    int index = 0;
    for (IRecord record: skierList) {
      SkierRecord skier = (SkierRecord)record;
      skierRowList.add(new SkierRowRecord(skier.getParameter(), index));
      index += skier.getNumRides();
    }
  }

  /**
   * Gets all skiers' detailed information.
   *
   * @return the list that stores all skiers' detailed information
   */
  @Override
  public List<IRecord> getRawList() {
    return rawList;
  }

  /**
   * Gets all skiers' summary information.
   *
   * @return the list that stores all skiers' summary information
   */
  @Override
  public List<IRecord> getSkierList() {
    return skierList;
  }

  /**
   * Gets all lifts' information.
   *
   * @return the list that stores all lifts' information
   */
  @Override
  public List<IRecord> getLiftList() {
    return liftList;
  }

  /**
   * Gets all hours' information.
   *
   * @return the list that stores all hours' information
   */
  @Override
  public List<IRecord> getHourList() {
    return HourRecord.toHourRecordList(hourLiftsList);
  }

  /**
   * Gets all skiers' row indices information.
   *
   * @return the list stores all skiers' row indices information
   */
  @Override
  public List<IRecord> getSkierRowList() {
    return skierRowList;
  }

  /**
   * Gets the processor's runtime.
   *
   * @return Duration the actual runtime
   */
  @Override
  public Duration getRunTime() {
    return runTime;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    SequentialDataProcessor that = (SequentialDataProcessor) other;

    return inputData.equals(that.inputData);
  }

  @Override
  public int hashCode() {
    return inputData.hashCode();
  }
}
