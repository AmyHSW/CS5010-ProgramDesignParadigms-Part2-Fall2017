package edu.neu.ccs.cs5010.assignment8.queryprocessor;

import edu.neu.ccs.cs5010.assignment8.query.IQuery;
import edu.neu.ccs.cs5010.assignment8.reader.IReader;
import edu.neu.ccs.cs5010.assignment8.reader.ReaderFactory;

import java.io.IOException;
import java.util.List;

public class QueryThread implements Runnable {

  private final List<IQuery> queries;
  private final int start;
  private final int end;
  private final List<String> output;

  public QueryThread(List<IQuery> queryList,
                     int start,
                     int end,
                     List<String> output) throws IOException {
    this.queries = queryList;
    this.start = start;
    this.end = end;
    this.output = output;
  }

  @Override
  public void run() {
    try {
      QueryProcessor.barrier.await();
      for (int i = start; i < end; i++) {
        IQuery query = queries.get(i);
        int queryId = query.getQueryId();
        int parameter = query.getParameter();
        IReader reader = ReaderFactory.getReader(queryId, parameter);
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
