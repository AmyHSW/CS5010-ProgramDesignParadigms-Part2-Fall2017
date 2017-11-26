package edu.neu.ccs.cs5010.assignment8;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.Database.Database;
import edu.neu.ccs.cs5010.assignment8.Database.HourDatabase;
import edu.neu.ccs.cs5010.assignment8.Database.LiftDatabase;
import edu.neu.ccs.cs5010.assignment8.Database.RawDatabase;
import edu.neu.ccs.cs5010.assignment8.Database.SkierDatabase;
import edu.neu.ccs.cs5010.assignment8.cmdHandler.CmdHandler;
import edu.neu.ccs.cs5010.assignment8.cmdHandler.ICmdHandler;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.IDataProcessor;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.SequentialDataProcessor;
import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputArgumentException;
import edu.neu.ccs.cs5010.assignment8.query.IQuery;
import edu.neu.ccs.cs5010.assignment8.query.IQueryDataReader;
import edu.neu.ccs.cs5010.assignment8.query.QueryDataReader;
import edu.neu.ccs.cs5010.assignment8.queryProcessor.IQueryProcessor;
import edu.neu.ccs.cs5010.assignment8.queryProcessor.QueryProcessor;
import edu.neu.ccs.cs5010.assignment8.writer.HourWriter;
import edu.neu.ccs.cs5010.assignment8.writer.Writer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SkiQueryProcessor {

  private static final String INPUT = "PDPAssignment.csv";

  public static void main(String[] args) throws InterruptedException, IOException {
    // validates and parses command-line arguments
    ICmdHandler cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputArgumentException(cmdHandler.getErrorMessage());
    }

    // creates queries based on test data file
    IQueryDataReader queryDataReader =
        new QueryDataReader(cmdHandler.getTestFilename(), cmdHandler.getNumQueries());
    List<IQuery> queryList = queryDataReader.getQueries();

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

    // processes queries
    IQueryProcessor queryProcessor = new QueryProcessor(queryList);
    queryProcessor.processQueries();

    //output
  }
}
