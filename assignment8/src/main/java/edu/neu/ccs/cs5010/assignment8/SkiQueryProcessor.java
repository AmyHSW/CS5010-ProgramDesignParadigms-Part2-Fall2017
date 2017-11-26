package edu.neu.ccs.cs5010.assignment8;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.Database.*;
import edu.neu.ccs.cs5010.assignment8.cmdHandler.CmdHandler;
import edu.neu.ccs.cs5010.assignment8.cmdHandler.ICmdHandler;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.IDataProcessor;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.SequentialDataProcessor;
import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputArgumentException;
import edu.neu.ccs.cs5010.assignment8.query.IQuery;
import edu.neu.ccs.cs5010.assignment8.query.IQueryGenerator;
import edu.neu.ccs.cs5010.assignment8.query.QueryGenerator;
import edu.neu.ccs.cs5010.assignment8.queryProcessor.IQueryProcessor;
import edu.neu.ccs.cs5010.assignment8.queryProcessor.QueryProcessor;
import edu.neu.ccs.cs5010.assignment8.writer.HourWriter;
import edu.neu.ccs.cs5010.assignment8.writer.Writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    long queryStart = System.currentTimeMillis();
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser queryParser = new CsvParser(settings);
    queryParser.beginParsing(new File(cmdHandler.getTestFilename()));
    IQueryGenerator queryGenerator =
        new QueryGenerator(queryParser, cmdHandler.getNumQueries());
    List<IQuery> queries = new ArrayList<>();
    while (queryGenerator.hasNextQuery()) {
      queries.add(queryGenerator.nextQuery());
    }
    System.out.println("Creating queries from test data file took "
        + (System.currentTimeMillis() - queryStart) + " milliseconds");

    // reads csv file to List<String[]>
    long inputStart = System.currentTimeMillis();
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
    System.out.println("Building dat files took "
        + (System.currentTimeMillis() - datStart) + " milliseconds");

    // processes queries
    DatabasePool databasePool = new DatabasePool();
    databasePool.addDatabase(skierDatabase);
    databasePool.addDatabase(rawDatabase);
    databasePool.addDatabase(hourDatabase);
    databasePool.addDatabase(liftDatabase);
    IQueryProcessor queryProcessor = new QueryProcessor(databasePool, queries);
    queryProcessor.processQueries();
    System.out.println("Processing queries took "
        + queryProcessor.getRuntime().toMillis() + " milliseconds");

    rawDatabase.close();
    skierDatabase.close();
    liftDatabase.close();
    hourDatabase.close();

    //output
  }
}
