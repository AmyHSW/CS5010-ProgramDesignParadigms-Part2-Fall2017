package edu.neu.ccs.cs5010.processor;

import java.util.HashMap;
import java.util.List;

public class SequentialProcessor extends Processor {

  private final List<String[]> inputData;

  public SequentialProcessor(List<String[]> inputData) {
    this.inputData = inputData.subList(1, inputData.size());
    skierNumRides = new HashMap<>();
    skierVerticalMeters = new HashMap<>();
    liftNumRides = new HashMap<>();
    hourRides = new HashMap<>();
  }

  @Override
  public void processInput() {
    long startTime = System.currentTimeMillis();
    for (String[] record : inputData) {
      process(record);
    }
    System.out.println("sequential runs " + (System.currentTimeMillis() - startTime));
  }

  private void process(String[] record) {
    String skier = record[SKIER_INDEX];
    String lift = record[LIFT_INDEX];
    String hour = ((Integer.parseInt(record[TIME_INDEX]) - 1) / MINUTES_IN_HOUR + 1) + "";
    processSkier(skier, lift);
    processLift(lift);
    if (!hourRides.containsKey(hour)) {
      hourRides.put(hour, new HashMap<>());
    }
    processHour(hour,lift);

  }
}
