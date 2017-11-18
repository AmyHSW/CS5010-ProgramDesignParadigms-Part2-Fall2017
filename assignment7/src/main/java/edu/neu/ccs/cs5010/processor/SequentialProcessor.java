package edu.neu.ccs.cs5010.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequentialProcessor implements Processor {

  private final List<String[]> inputData;
  private Map<String, Integer> skierNumRides = new HashMap<>();
  private Map<String, Integer> skierVerticalMeters = new HashMap<>();
  private Map<String, Integer> liftNumRides = new HashMap<>();
  private Map<Integer, Map<String, Integer>> hourRides = new HashMap<>();

  public SequentialProcessor(List<String[]> inputData) {
    this.inputData = inputData.subList(1, inputData.size());
  }

  @Override
  public void processInput() {
    for (String[] record : inputData) {
      process(record);
    }
  }

  private void process(String[] record) {
    String skier = record[2];
    String lift = record[3];
    int hour = Integer.parseInt(record[4]) / 60 + 1;
    skierNumRides.put(skier, skierNumRides.getOrDefault(skier, 0) + 1);
    if (lift.compareTo("10") <= 0) {
      skierVerticalMeters.put(skier, skierVerticalMeters.getOrDefault(skier, 0) + 200);
    } else if (lift.compareTo("20") <= 0) {
      skierVerticalMeters.put(skier, skierVerticalMeters.getOrDefault(skier, 0) + 300);
    } else if (lift.compareTo("30") <= 0) {
      skierVerticalMeters.put(skier, skierVerticalMeters.getOrDefault(skier, 0) + 400);
    } else {
      skierVerticalMeters.put(skier, skierVerticalMeters.getOrDefault(skier, 0) + 500);
    }
    liftNumRides.put(lift, liftNumRides.getOrDefault(lift, 0) + 1);
    if (!hourRides.containsKey(hour)) {
      hourRides.put(hour, new HashMap<>());
    }
    hourRides.get(hour).put(lift, hourRides.get(hour).getOrDefault(lift, 0) + 1);
  }
}
