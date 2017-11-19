package edu.neu.ccs.cs5010.processors;

import java.util.Map;

public interface IProcessor {
  void processInput() throws InterruptedException;
  Map<String, Integer> getSkierVerticalMeters();
  Map<String, Integer> getLiftNumRides();
  Map<String, Map<String, Integer>> getHourRides();
}
