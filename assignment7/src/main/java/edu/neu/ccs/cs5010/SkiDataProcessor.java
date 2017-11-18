package edu.neu.ccs.cs5010;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import edu.neu.ccs.cs5010.processor.ConcurrentProcessor;
import edu.neu.ccs.cs5010.processor.Processor;
import edu.neu.ccs.cs5010.processor.SequentialProcessor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SkiDataProcessor {

  private static final String INPUT = "PDPAssignment.csv";

  public static void main(String[] args) {
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
    IoLibrary ioLibrary = new IoLibrary();
    ioLibrary.generateOutput("skier1.csv", processors.get(0).getSkierOutput());
    ioLibrary.generateOutput("skier2.csv", processors.get(1).getSkierOutput());
    ioLibrary.generateOutput("lifts1.csv", processors.get(0).getLiftOutput());
    ioLibrary.generateOutput("lifts2.csv", processors.get(1).getLiftOutput());
    ioLibrary.generateOutput("hours1.csv", processors.get(0).getHourOutput());
    ioLibrary.generateOutput("hours2.csv", processors.get(1).getHourOutput());
  }
}
