package edu.neu.ccs.cs5010;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.processor.ConcurrentProcessor;
import edu.neu.ccs.cs5010.processor.Processor;
import edu.neu.ccs.cs5010.processor.SequentialProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SkiDataProcessor {

  private static final String INPUT = "PDPAssignment.csv";

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    parser.beginParsing(new File(INPUT));
    List<String[]> inputData = new ArrayList<>();
    String[] record;
    while ((record = parser.parseNext()) != null) {
      inputData.add(record);
    }
    System.out.println(System.currentTimeMillis() - startTime);

    List<Processor> processors = new ArrayList<>();
    processors.add(new SequentialProcessor(inputData));
    processors.add(new ConcurrentProcessor(inputData));
    for (Processor processor : processors) {
      processor.processInput();
    }
  }
}
