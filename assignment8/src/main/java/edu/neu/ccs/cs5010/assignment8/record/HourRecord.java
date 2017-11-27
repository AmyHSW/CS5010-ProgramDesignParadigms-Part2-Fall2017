package edu.neu.ccs.cs5010.assignment8.record;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * The HourRecord class represents a hour record.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class HourRecord implements IRecord {

  /**
   * The total number of working hours per day.
   */
  public static final int HOUR_TOTAL = 6;
  /**
   * The size of a hour record in the dat file.
   */
  public static final int SIZE = Integer.BYTES * 11;

  private static final int TEN = 10;
  private static final int MINUTES_IN_HOUR = 60;

  private int hourId;
  private List<Integer> topTenLifts;

  /**
   * The default constructor of HourRecord object.
   */
  public HourRecord() {
    hourId = 0;
    topTenLifts = new ArrayList<>(TEN);
  }

  /**
   * Constructs a new hour record with the hour index and the list of lift ids.
   * @param hourId the hour id
   * @param liftList the list of top 10 most popular lift ids
   */
  public HourRecord(int hourId, List<Integer> liftList) {
    this.hourId = hourId;
    topTenLifts = liftList;
  }

  /**
   * Returns the hour id of this record.
   * @return the hour id of this record.
   */
  @Override
  public int getParameter() {
    return hourId;
  }

  /**
   * Reads from the dat file to update the fields of this record.
   * @param file the dat file
   * @throws IOException if there is an I/O failure
   */
  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    hourId = file.readInt();
    for (int i = 0; i < TEN; i++) {
      topTenLifts.add(file.readInt());
    }
  }

  /**
   * Writes the information of this record to the file.
   * @param file the dat file.
   * @throws IOException if there is an I/O failure.
   */
  @Override
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(hourId);
    for (int liftId: topTenLifts) {
      file.writeInt(liftId);
    }
  }

  /**
   * Converts the time to hour index in the list.
   *
   * @param time the minutes after 9 am
   */
  public static int toIndex(int time) {
    return (time - 1) / MINUTES_IN_HOUR;
  }

  /**
   * Converts the original list of lift lists to hour record list. Sorts the list of lifts for
   * each hour by the number of rides, and constructs a new hour record using the top ten
   * most popular lift ids.
   *
   * @param hourLiftsList a list of list of lift records
   * @return a hour record list
   */
  public static List<IRecord> toHourRecordList(List<List<IRecord>> hourLiftsList) {
    List<IRecord> hourRecords = new ArrayList<>();
    for (int i = 0; i < HourRecord.HOUR_TOTAL; i++) {
      List<IRecord> liftList = hourLiftsList.get(i);
      liftList.sort((lift1, lift2) ->
          ((LiftRecord)lift2).getNumber() - ((LiftRecord)lift1).getNumber());
      List<Integer> topTenList = new ArrayList<>();
      for (int j = 0; j < TEN; j++) {
        topTenList.add(liftList.get(j).getParameter());
      }
      hourRecords.add(new HourRecord(i + 1, topTenList));
    }
    return hourRecords;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    HourRecord that = (HourRecord) other;

    return hourId == that.hourId;
  }

  @Override
  public int hashCode() {
    return hourId;
  }

  @Override
  public String toString() {
    return hourId + ":" + topTenLifts.toString();
  }
}
