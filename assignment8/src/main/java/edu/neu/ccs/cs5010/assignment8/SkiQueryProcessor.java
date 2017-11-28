package edu.neu.ccs.cs5010.assignment8;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.cmdhandler.IQueryCmdHandler;
import edu.neu.ccs.cs5010.assignment8.cmdhandler.QueryCmdHandler;
import edu.neu.ccs.cs5010.assignment8.exception.InvalidInputArgumentException;
import edu.neu.ccs.cs5010.assignment8.output.IoLibrary;
import edu.neu.ccs.cs5010.assignment8.query.IQuery;
import edu.neu.ccs.cs5010.assignment8.query.IQueryGenerator;
import edu.neu.ccs.cs5010.assignment8.query.QueryGenerator;
import edu.neu.ccs.cs5010.assignment8.queryprocessor.IQueryProcessor;
import edu.neu.ccs.cs5010.assignment8.queryprocessor.QueryProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The SkiQueryProcessor class processes the queries in the original query file and
 * saves the results in 20 txt files.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class SkiQueryProcessor {

  /**
   * Reads in the query file name and number of queries through command-line arguments. Parses
   * the csv file using a CsvParser and generates queries using a query generator. Processes the
   * queries and saves the results in 20 txt files.
   *
   * @param args the command-line arguments
   * @throws InterruptedException if the thread is interrupted
   * @throws IOException if there is an I/O failure
   */
  public static void main(String[] args) throws InterruptedException, IOException {

    // validates and parses command-line arguments
    IQueryCmdHandler cmdHandler = new QueryCmdHandler(args);
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

    //output the txt files
    List<List<String>> threadsOutput = queryProcessor.getOutputList();
    int threadId = 1;
    for (List<String> output : threadsOutput) {
      IoLibrary.generateOutput("thread" + threadId++ + ".txt", output);
    }
  }
}
