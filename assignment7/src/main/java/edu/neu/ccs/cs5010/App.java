package edu.neu.ccs.cs5010;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Hello world! */
public class App {
  public static void main(String[] args) {
    Map<String, Integer> skierNumRides = new HashMap<>();
    Map<String, Integer> skierVerticalMeters = new HashMap<>();
    Map<String, Integer> liftNumRides = new HashMap<>();
    Map<Integer, Map<String, Integer>> hourRides = new HashMap<>();
    long startTime = System.currentTimeMillis();
    CsvParserSettings settings = new CsvParserSettings();
    // creates a CSV parser
    CsvParser parser = new CsvParser(settings);
    parser.beginParsing(new File("PDPAssignment.csv"));
    String[] headers = parser.parseNext();
    String[] row;
    while ((row = parser.parseNext()) != null) {
      String skier = row[2];
      String lift = row[3];
      int hour = Integer.parseInt(row[4]) / 60 + 1;
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
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    System.out.println(duration);

  }
}
