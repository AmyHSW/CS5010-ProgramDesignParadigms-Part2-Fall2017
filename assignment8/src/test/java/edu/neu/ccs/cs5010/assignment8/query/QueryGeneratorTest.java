package edu.neu.ccs.cs5010.assignment8.query;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.exception.NoMoreQueriesException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class QueryGeneratorTest {

  private static final String QUERY_FILE = "PDPAssignment8.csv";
  private IQueryGenerator generator;

  @Before
  public void setUp() throws Exception {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser queryParser = new CsvParser(settings);
    queryParser.beginParsing(new File(QUERY_FILE));
    generator = new QueryGenerator(queryParser, 1);
  }

  @Test
  public void hasNextQuery() throws Exception {
    assertTrue(generator.hasNextQuery());
  }

  @Test
  public void nextQuery() throws Exception {
    IQuery query = generator.nextQuery();
    assertTrue(query.equals(new Query(1, 33248)));
  }

  @Test(expected = NoMoreQueriesException.class)
  public void expectedNoMoreQueriesException() throws Exception {
    generator.nextQuery();
    generator.nextQuery();
  }

}