package edu.neu.ccs.cs5010.assignment8.writer;

import edu.neu.ccs.cs5010.assignment8.Database.IDatabase;
import edu.neu.ccs.cs5010.assignment8.Record.HourRecord;
import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.Record.LiftRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HourWriter {

  public static void writeToHourData(List<List<IRecord>> list, IDatabase database) throws IOException {
    for (int i = 0; i < HourRecord.HOUR_TOTAL; i++) {
      List<IRecord> liftList = list.get(i);
      liftList.sort((lift1, lift2) -> ((LiftRecord)lift2).getNumber() - ((LiftRecord)lift1).getNumber());
      List<Integer> topTenList = new ArrayList<>();
      for (int j = 0; j < HourRecord.TEN; j++) {
        topTenList.add(liftList.get(j).getParameter());
      }
      database.addRecord(new HourRecord(i, topTenList));
    }
  }

}
