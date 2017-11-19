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
    for (int i = 0; i < processors.size(); i++) {
      IoLibrary.generateOutput("skiers" + i + ".csv", processors.get(i).getSkierOutput());
      IoLibrary.generateOutput("lifts" + i + ".csv", processors.get(i).getLiftOutput());
      IoLibrary.generateOutput("hours" + i + ".csv", processors.get(i).getHourOutput());
    }
  }
}
