package edu.neu.ccs.cs5010.assignment8.Record;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class HourRecord implements IRecord {

  private int hourId;
  private List<Integer> topTenLifts;

  public static final int HOUR_TOTAL = 6;
  public static final int SIZE = Integer.BYTES * 11;

  public static final int TEN = 10;
  private static final int MINUTES_IN_HOUR = 60;

  /**
   * The default constructor of HourRecord object.
   */
  public HourRecord() {
    hourId = 0;
    topTenLifts = new ArrayList<>(TEN);
  }

  public HourRecord(int hourIndex, List<Integer> liftList) {
    hourId = hourIndex + 1;
    topTenLifts = liftList;
  }

  @Override
  public int getParameter() {
    return hourId;
  }

  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    hourId = file.readInt();
    for (int i = 0; i < TEN; i++) {
      topTenLifts.add(file.readInt());
    }
  }

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
}
