package edu.neu.ccs.cs5010.assignment8;

import edu.neu.ccs.cs5010.assignment8.cmdHandler.CmdHandler;
import edu.neu.ccs.cs5010.assignment8.cmdHandler.ICmdHandler;
import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputArgumentException;
import edu.neu.ccs.cs5010.assignment8.query.IQuery;
import edu.neu.ccs.cs5010.assignment8.query.IQueryDataReader;
import edu.neu.ccs.cs5010.assignment8.query.QueryDataReader;
import edu.neu.ccs.cs5010.assignment8.queryProcessor.IQueryProcessor;
import edu.neu.ccs.cs5010.assignment8.queryProcessor.QueryProcessor;

import java.util.List;

public class SkiQueryProcessor {

  public static void main(String[] args) {
    // validates and parses command-line arguments
    ICmdHandler cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputArgumentException(cmdHandler.getErrorMessage());
    }

    // creates queries based on test data file
    IQueryDataReader queryDataReader =
        new QueryDataReader(cmdHandler.getTestFilename(), cmdHandler.getNumQueries());
    List<IQuery> queryList = queryDataReader.getQueries();

    // processes queries
    IQueryProcessor processor = new QueryProcessor(queryList);
    processor.processQueries();

    //output
  }
}
