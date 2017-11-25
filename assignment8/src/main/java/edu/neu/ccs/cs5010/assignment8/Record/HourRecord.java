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

  private static final int Ten = 10;
  private static final int MINUTES_IN_HOUR = 60;

  /**
   * The default constructor of HourRecord object.
   */
  public HourRecord() {
    hourId = 0;
    topTenLifts = new ArrayList<>(Ten);
  }

  public HourRecord(int hourIndex) {
    hourId = hourIndex + 1;
    topTenLifts = new ArrayList<>(Ten);
  }

  @Override
  public int getParameter() {
    return hourId;
  }

  @Override
  public void readFromFile(RandomAccessFile file) throws IOException {
    hourId = file.readInt();
    for (int i = 0; i < Ten; i++) {
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
  public static int toIndex(String time) {
    return (Integer.parseInt(time) - 1) / MINUTES_IN_HOUR;
  }
}
