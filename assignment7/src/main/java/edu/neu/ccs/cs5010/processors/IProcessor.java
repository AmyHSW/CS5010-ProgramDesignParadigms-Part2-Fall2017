package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.lift.Lift;
import edu.neu.ccs.cs5010.skier.Skier;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface IProcessor {
  void processInput() throws InterruptedException;
  Map<String, Skier> getSkierMap();
  List<Lift> getLiftList();
  List<Map<String, Integer>> getHourRides();
  Duration getRunTime();
}
