package edu.neu.ccs.cs5010;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.processors.ConcurrentProcessor;
import edu.neu.ccs.cs5010.processors.IProcessor;
import edu.neu.ccs.cs5010.processors.SequentialProcessor;
import edu.neu.ccs.cs5010.result.IResultAnalyser;
import edu.neu.ccs.cs5010.result.IoLibrary;
import edu.neu.ccs.cs5010.result.ResultAnalyser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The SkiDataProcessor is a class to analyze ski resort data.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SkiDataProcessor {

  private static final String INPUT = "PDPAssignment.csv";

  /**
   * Reads ski resort data and analyze in both sequential and concurrent ways.
   *
   */
  public static void main(String[] args) throws InterruptedException {
    // reads csv file to List<String[]>
    long startTime = System.currentTimeMillis();
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    List<String[]> inputData = parser.parseAll(new File(INPUT));
    System.out.println("Parsing input csv file took "
        + (System.currentTimeMillis() - startTime) + " milliseconds");

    // processes data using two processors: sequential and concurrent
    List<IProcessor> processors = new ArrayList<>();
    processors.add(new SequentialProcessor(inputData));
    processors.add(new ConcurrentProcessor(inputData));
    for (IProcessor processor : processors) {
      processor.processInput();
      System.out.println(processor + " ran "
          + processor.getRunTime().toMillis() + " milliseconds");
    }

    // generates the output csv files
    IResultAnalyser resultAnalyser = new ResultAnalyser();
    for (int i = 0; i < processors.size(); i++) {
      IoLibrary.generateOutput("skiers" + i + ".csv",
              resultAnalyser.getSkierOutput(processors.get(i).getSkierMap()));
      IoLibrary.generateOutput("lifts" + i + ".csv",
              resultAnalyser.getLiftOutput(processors.get(i).getLiftList()));
      IoLibrary.generateOutput("hours" + i + ".csv",
              resultAnalyser.getHourOutput(processors.get(i).getHourRides()));
    }
  }
}
