package edu.neu.ccs.cs5010.assignment8.database;

import edu.neu.ccs.cs5010.assignment8.record.HourRecord;
import edu.neu.ccs.cs5010.assignment8.record.IRecord;
import edu.neu.ccs.cs5010.assignment8.record.LiftRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HourDatabase extends Database {

  public HourDatabase(String fileString) throws IOException {
    super(fileString);
  }

  public void writeHourRecordsToFile(List<List<IRecord>> list) throws IOException {
    List<IRecord> hourRecords = new ArrayList<>();
    for (int i = 0; i < HourRecord.HOUR_TOTAL; i++) {
      List<IRecord> liftList = list.get(i);
      liftList.sort((lift1, lift2) -> ((LiftRecord)lift2).getNumber() - ((LiftRecord)lift1).getNumber());
      List<Integer> topTenList = new ArrayList<>();
      for (int j = 0; j < HourRecord.TEN; j++) {
        topTenList.add(liftList.get(j).getParameter());
      }
      hourRecords.add(new HourRecord(i, topTenList));
    }
    writeRecordsToFile(hourRecords);
  }
}
