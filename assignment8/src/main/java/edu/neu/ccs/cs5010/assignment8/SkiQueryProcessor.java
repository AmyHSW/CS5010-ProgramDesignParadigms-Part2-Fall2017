package edu.neu.ccs.cs5010.assignment8;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.Database.*;
import edu.neu.ccs.cs5010.assignment8.cmdHandler.CmdHandler;
import edu.neu.ccs.cs5010.assignment8.cmdHandler.ICmdHandler;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.IDataProcessor;
import edu.neu.ccs.cs5010.assignment8.dataProcessor.SequentialDataProcessor;
import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputArgumentException;
import edu.neu.ccs.cs5010.assignment8.ioUtil.IoLibrary;
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

    // processes queries
    IQueryProcessor queryProcessor = new QueryProcessor(queries);
    queryProcessor.processQueries();
    System.out.println("Processing queries took "
        + queryProcessor.getRuntime().toMillis() + " milliseconds");

    //output
    List<List<String>> threadsOutput = queryProcessor.getOutputList();
    int i = 1;
    for (List<String> output : threadsOutput) {
      IoLibrary.generateOutput("thread" + i++ + ".txt", output);
    }
  }
}
