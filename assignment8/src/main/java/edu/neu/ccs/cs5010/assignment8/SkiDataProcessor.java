package edu.neu.ccs.cs5010.assignment8;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.Database.Database;
import edu.neu.ccs.cs5010.assignment8.Database.HourDatabase;
import edu.neu.ccs.cs5010.assignment8.Database.LiftDatabase;
import edu.neu.ccs.cs5010.assignment8.Database.RawDatabase;
import edu.neu.ccs.cs5010.assignment8.Database.SkierDatabase;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.IDataProcessor;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.SequentialDataProcessor;
import edu.neu.ccs.cs5010.assignment8.writer.HourWriter;
import edu.neu.ccs.cs5010.assignment8.writer.Writer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SkiDataProcessor {

  private static final String INPUT = "PDPAssignment.csv";

  public static void main(String[] args) throws InterruptedException, IOException{
    // reads csv file to List<String[]>
    long inputStart = System.currentTimeMillis();
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser inputParser = new CsvParser(settings);
    List<String[]> inputData = inputParser.parseAll(new File(INPUT));
    System.out.println("Parsing raw input csv file took "
        + (System.currentTimeMillis() - inputStart) + " milliseconds");

    // processes data sequentially
    IDataProcessor processor = new SequentialDataProcessor(inputData);
    processor.processInput();
    System.out.println("Processing raw input file took "
        + processor.getRunTime().toMillis() + " milliseconds (sequentially)");

    // generates the output dat files
    long datStart = System.currentTimeMillis();
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
    System.out.println("Building dat files took "
        + (System.currentTimeMillis() - datStart) + " milliseconds");
  }
}
