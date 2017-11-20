package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.lift.Hour;
import edu.neu.ccs.cs5010.lift.ILift;
import edu.neu.ccs.cs5010.lift.Lift;
import edu.neu.ccs.cs5010.skier.ISkier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Processor implements IProcessor {
  protected final List<ILift> liftList;
  protected final List<List<ILift>> hourRides;
  protected Duration runTime;

  public Processor() {
    liftList = new ArrayList<>();
    hourRides = new ArrayList<>();
    initHourRides();
    initLiftList();
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
  public abstract void processInput() throws InterruptedException;

  @Override
  public abstract Map<String, ISkier> getSkierMap();

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
}
