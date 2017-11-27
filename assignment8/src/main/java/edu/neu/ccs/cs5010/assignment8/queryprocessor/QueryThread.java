package edu.neu.ccs.cs5010.assignment8.queryprocessor;

import edu.neu.ccs.cs5010.assignment8.query.IQuery;
import edu.neu.ccs.cs5010.assignment8.reader.IReader;

import java.io.IOException;
import java.util.List;

/**
 * The QueryThread class is responsible for processing a segment of the queries.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class QueryThread implements Runnable {

  private final List<IQuery> queries;
  private final int start;
  private final int end;
  private final List<String> output;

  /**
   * Constructs a new query thread with a list of queries, start and end indexes, and
   * the outout list of strings.
   * @param queryList the query list
   * @param start the start index (inclusive)
   * @param end the end index (exclusive)
   * @param output a list of strings that store the results of queries
   * @throws IOException if there is an I/O failure
   */
  public QueryThread(List<IQuery> queryList,
                     int start,
                     int end,
                     List<String> output) throws IOException {
    this.queries = queryList;
    this.start = start;
    this.end = end;
    this.output = output;
  }

  /**
   * Starts processing the queries. For each query, gets a reader from the reader factory
   * to read the corresponding dat file, and stores the result in the output list.
   */
  @Override
  public void run() {
    try {
      QueryProcessor.barrier.await();
      for (int i = start; i < end; i++) {
        IQuery query = queries.get(i);
        int queryId = query.getQueryId();
        int parameter = query.getParameter();
        IReader reader = IReader.getReader(queryId, parameter);
        String recordInfo = reader.read() + "\n";
        output.add(recordInfo);
      }
      QueryProcessor.barrier.await();
    } catch (Exception exception) {
      System.out.println("Something went wrong! : " + exception.getMessage());
      exception.printStackTrace();
    }
  }

}
