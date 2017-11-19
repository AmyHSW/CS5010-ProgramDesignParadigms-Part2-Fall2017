package edu.neu.ccs.cs5010;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.processors.ConcurrentProcessor;
import edu.neu.ccs.cs5010.processors.Processor;
import edu.neu.ccs.cs5010.processors.SequentialProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SkiDataProcessor {

  private static final String INPUT = "PDPAssignment.csv";

  public static void main(String[] args) throws InterruptedException {
    long startTime = System.currentTimeMillis();
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    List<String[]> inputData = parser.parseAll(new File(INPUT));
    System.out.println(System.currentTimeMillis() - startTime);

    List<Processor> processors = new ArrayList<>();
    processors.add(new SequentialProcessor(inputData));
    processors.add(new ConcurrentProcessor(inputData));
    for (Processor processor : processors) {
      processor.processInput();
    }
    IoLibrary.generateOutput("skier1.csv", processors.get(0).getSkierOutput());
    IoLibrary.generateOutput("skier2.csv", processors.get(1).getSkierOutput());
    IoLibrary.generateOutput("lifts1.csv", processors.get(0).getLiftOutput());
    IoLibrary.generateOutput("lifts2.csv", processors.get(1).getLiftOutput());
    IoLibrary.generateOutput("hours1.csv", processors.get(0).getHourOutput());
    IoLibrary.generateOutput("hours2.csv", processors.get(1).getHourOutput());
  }
}
