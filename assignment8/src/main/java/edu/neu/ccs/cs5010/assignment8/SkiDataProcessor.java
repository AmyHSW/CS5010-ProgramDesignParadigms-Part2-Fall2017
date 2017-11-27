package edu.neu.ccs.cs5010.assignment8;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.editor.*;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.IDataProcessor;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.SequentialDataProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SkiDataProcessor {

  private static final String INPUT = "PDPAssignment.csv";
  private static final String RAW_DATA_FILE = "liftRides.dat";
  private static final String SKIER_DATA_FILE = "skiers.dat";
  private static final String LIFT_DATA_FILE = "lifts.dat";
  private static final String HOUR_DATA_FILE = "hours.dat";
  private static final String SKIER_ROW_FILE = "skierRow.dat";

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
    IDatFileEditor rawFileEditor = new DatFileEditor(RAW_DATA_FILE);
    IDatFileEditor skierFileEditor = new DatFileEditor(SKIER_DATA_FILE);
    IDatFileEditor liftFileEditor = new DatFileEditor(LIFT_DATA_FILE);
    IDatFileEditor hourFileEditor = new DatFileEditor(HOUR_DATA_FILE);
    IDatFileEditor skierRowFileEditor = new DatFileEditor(SKIER_ROW_FILE);

    rawFileEditor.writeRecordsToFile(processor.getRawList());
    skierFileEditor.writeRecordsToFile(processor.getSkierList());
    liftFileEditor.writeRecordsToFile(processor.getLiftList());
    hourFileEditor.writeRecordsToFile(processor.getHourList());
    skierRowFileEditor.writeRecordsToFile(processor.getSkierRowList());

    rawFileEditor.close();
    skierFileEditor.close();
    liftFileEditor.close();
    hourFileEditor.close();
    skierRowFileEditor.close();

    System.out.println("Building dat files took "
        + (System.currentTimeMillis() - datStart) + " milliseconds");
  }
}
