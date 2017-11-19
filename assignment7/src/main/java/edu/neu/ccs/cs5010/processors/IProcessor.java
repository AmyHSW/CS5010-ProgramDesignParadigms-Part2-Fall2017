package edu.neu.ccs.cs5010.processors;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface IProcessor {
  void processInput() throws InterruptedException;
  Map<String, Integer> getSkierVerticalMeters();
  Map<String, Integer> getLiftNumRides();
  List<Map<String, Integer>> getHourRides();
  Duration getRunTime();
}
