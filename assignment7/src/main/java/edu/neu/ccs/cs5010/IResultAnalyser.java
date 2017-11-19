package edu.neu.ccs.cs5010;

import java.util.List;
import java.util.Map;

public interface IResultAnalyser {
  List<String> getSkierOutput(Map<String, Integer> skierVerticalMeters);
  List<String> getLiftOutput(Map<String, Integer> liftNumRides);
  List<String> getHourOutput(List<Map<String, Integer>> hourRides);
}
