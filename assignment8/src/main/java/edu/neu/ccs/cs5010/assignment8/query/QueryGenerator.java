package edu.neu.ccs.cs5010.assignment8.query;

import com.univocity.parsers.csv.CsvParser;
import edu.neu.ccs.cs5010.assignment8.exception.NoMoreQueriesException;

public class QueryGenerator implements IQueryGenerator {

  private final CsvParser parser;
  private final int numQueries;
  private String[] row;
  private int numQueriesGenerated = 0;

  public QueryGenerator(CsvParser parser, int numQueries) {
    this.parser = parser;
    this.numQueries = numQueries;
    row = parser.parseNext();
  }

  @Override
  public boolean hasNextQuery() {
    return numQueriesGenerated < numQueries && row != null;
  }

  @Override
  public IQuery nextQuery() {
    if (!hasNextQuery()) {
      throw new NoMoreQueriesException("there is no next query");
    }
    IQuery query = IQuery.createQuery(Integer.parseInt(row[0]), Integer.parseInt(row[1]));
    row = parser.parseNext();
    numQueriesGenerated++;
    return query;
  }
}
