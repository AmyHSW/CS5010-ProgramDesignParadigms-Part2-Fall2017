package edu.neu.ccs.cs5010.assignment8.queryprocessor;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

/**
 * The IQueryProcessor interface represents a query processor.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IQueryProcessor {

  /**
   * Processes the queries in the query list.
   * @throws InterruptedException if the thread is interrupted
   * @throws IOException if there is an I/O failure
   */
  void processQueries() throws InterruptedException, IOException;

  /**
   * Returns the run time of this processor.
   * @return the run time of this processor.
   */
  Duration getRuntime();

  /**
   * Returns the results of the queries.
   * @return the results of the queries.
   */
  List<List<String>> getOutputList();
}
