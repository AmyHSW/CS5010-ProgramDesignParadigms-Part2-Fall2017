package edu.neu.ccs.cs5010.assignment8.query;

import com.univocity.parsers.csv.CsvParser;
import edu.neu.ccs.cs5010.assignment8.exception.NoMoreQueriesException;

/**
 * The QueryGenerator class is responsible for generating the queries from the query file.
 *
 * <p>Each line in query file represents one query. The CsvParser is used to parse each line
 * in the query file into list of strings. The list of strings is then used to create new query.
 *
 * <p>If the number of queries generated reaches the number of queries requested, or if the
 * query file has no more lines to parse, the generator will have no more query to generate.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class QueryGenerator implements IQueryGenerator {

  private final CsvParser parser;
  private final int numQueries;
  private String[] row;
  private int numQueriesGenerated = 0;

  /**
   * Constructs a new query generator with a csv parser and the number of queries.
   * @param parser a csv parser
   * @param numQueries the number of queries
   */
  public QueryGenerator(CsvParser parser, int numQueries) {
    this.parser = parser;
    this.numQueries = numQueries;
    row = parser.parseNext();
  }

  /**
   * Returns true if there is next query to generate.
   * @return true if there is next query to generate; false otherwise.
   */
  @Override
  public boolean hasNextQuery() {
    return numQueriesGenerated < numQueries && row != null;
  }

  /**
   * Returns next query.
   * @return next query.
   */
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
