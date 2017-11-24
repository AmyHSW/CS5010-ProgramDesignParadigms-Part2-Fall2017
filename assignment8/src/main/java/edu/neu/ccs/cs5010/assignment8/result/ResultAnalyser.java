package edu.neu.ccs.cs5010.assignment8.result;

import edu.neu.ccs.cs5010.assignment8.hour.Hour;
import edu.neu.ccs.cs5010.assignment8.lift.ILift;
import edu.neu.ccs.cs5010.assignment8.skier.ISkier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The ResultAnalyser represents a concrete result analyser.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class ResultAnalyser implements IResultAnalyser {

  private static final int HUNDRED = 100;
  private static final int TEN = 10;
  private static final String SKIER_VERTICAL_HEADER = "SkierID,Vertical";
  private static final String LIFT_RIDES_HEADER = "LiftID,Number of Rides";

  @Override
  public List<String> getSkierOutput(Map<String, ISkier> skierMap) {
    List<ISkier> skiers = new ArrayList<>(skierMap.values());
    Collections.sort(skiers);
    List<String> skierVerticalTotals = new ArrayList<>();
    skierVerticalTotals.add(SKIER_VERTICAL_HEADER);
    for (int i = 0; i < Math.min(HUNDRED, skiers.size()); i++) {
      String line = skiers.get(i).getSkierId() + "," + skiers.get(i).getVerticalMeters();
      skierVerticalTotals.add(line);
    }
    return skierVerticalTotals;
  }

  @Override
  public List<String> getLiftOutput(List<ILift> liftList) {
    List<String> liftsRides = new ArrayList<>();
    liftsRides.add(LIFT_RIDES_HEADER);
    for (ILift lift: liftList) {
      liftsRides.add(lift.getLiftId() + "," + lift.getNumber());
    }
    return liftsRides;
  }

  @Override
  public List<String> getHourOutput(List<List<ILift>> hourRides) {
    List<String> hourLiftRides = new ArrayList<>();
    for (int i = 0; i < Hour.HOUR_NUM; i++) {
      List<ILift> liftsRides = hourRides.get(i);
      Collections.sort(liftsRides);
      hourLiftRides.add("Hour " + (i + 1));
      hourLiftRides.add(LIFT_RIDES_HEADER);
      for (int j = 0; j < Math.min(TEN, liftsRides.size()); j++) {
        String line = liftsRides.get(j).getLiftId() + "," + liftsRides.get(j).getNumber();
        hourLiftRides.add(line);
      }
    }
    return hourLiftRides;
  }
}
