package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.lift.ILift;
import edu.neu.ccs.cs5010.skier.ISkier;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface IProcessor {
  void processInput() throws InterruptedException;
  Map<String, ISkier> getSkierMap();
  List<ILift> getLiftList();
  List<List<ILift>> getHourRides();
  Duration getRunTime();
}
