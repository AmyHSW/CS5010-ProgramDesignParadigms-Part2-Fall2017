package edu.neu.ccs.cs5010.assignment8;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.processors.IProcessor;
import edu.neu.ccs.cs5010.assignment8.processors.SequentialProcessor;
import edu.neu.ccs.cs5010.assignment8.result.IResultAnalyser;
import edu.neu.ccs.cs5010.assignment8.result.IoLibrary;
import edu.neu.ccs.cs5010.assignment8.result.ResultAnalyser;

import java.io.File;
import java.util.List;

/**
 * The SkiDataProcessor is a class to analyze ski resort data.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SkiDataProcessor {

  private static final String INPUT = "PDPAssignment.csv";

  /**
   * Reads ski resort data and analyze in a sequential way.
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

    // processes data sequentially
    IProcessor processor = new SequentialProcessor(inputData);
    processor.processInput();
    System.out.println(processor + " ran "
        + processor.getRunTime().toMillis() + " milliseconds");

    // generates the output dat files
    IResultAnalyser resultAnalyser = new ResultAnalyser();
    IoLibrary.generateOutput("skiers.dat",
              resultAnalyser.getSkierOutput(processor.getSkierList()));
    IoLibrary.generateOutput("lifts.dat",
              resultAnalyser.getLiftOutput(processor.getLiftList()));
    IoLibrary.generateOutput("hours.dat",
              resultAnalyser.getHourOutput(processor.getHourRides()));
  }
}
