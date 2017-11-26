package edu.neu.ccs.cs5010.assignment8;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.Database.*;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.IDataProcessor;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.SequentialDataProcessor;
import edu.neu.ccs.cs5010.assignment8.writer.HourWriter;
import edu.neu.ccs.cs5010.assignment8.writer.Writer;

import java.io.File;
import java.io.IOException;
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
   */
  public static void main(String[] args) throws InterruptedException, IOException {
    // reads csv file to List<String[]>
    long startTime = System.currentTimeMillis();
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    List<String[]> inputData = parser.parseAll(new File(INPUT));
    System.out.println("Parsing input csv file took "
        + (System.currentTimeMillis() - startTime) + " milliseconds");

    // processes data sequentially
    IDataProcessor processor = new SequentialDataProcessor(inputData);
    processor.processInput();
    System.out.println(processor + " ran "
        + processor.getRunTime().toMillis() + " milliseconds");

    // generates the output dat files
    Database rawDatabase = new RawDatabase("liftRides.dat");
    Database skierDatabase = new SkierDatabase("skiers.dat");
    Database liftDatabase = new LiftDatabase("lifts.dat");
    Database hourDatabase = new HourDatabase("hours.dat");
    Writer.writeToData(processor.getRawList(), rawDatabase);
    Writer.writeToData(processor.getSkierList(), skierDatabase);
    Writer.writeToData(processor.getLiftList(), liftDatabase);
    HourWriter.writeToHourData(processor.getHourRides(), hourDatabase);
    rawDatabase.close();
    skierDatabase.close();
    liftDatabase.close();
    hourDatabase.close();
  }
}
