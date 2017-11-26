package edu.neu.ccs.cs5010.assignment8.thread;

import edu.neu.ccs.cs5010.assignment8.Database.DatabasePool;
import edu.neu.ccs.cs5010.assignment8.Record.IRecord;
import edu.neu.ccs.cs5010.assignment8.query.IQuery;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class QueryThread extends Thread {

  private static int threads = 0;
  private final DatabasePool databasePool;
  private final List<IQuery> queries;
  private final int start;
  private final int end;
  private RandomAccessFile randomAccessFile;

  public QueryThread(List<IQuery> queryList,
                     int start,
                     int end,
                     DatabasePool databasePool) throws IOException {
    this.databasePool = databasePool;
    this.queries = queryList;
    this.start = start;
    this.end = end;
    threads++;
    randomAccessFile = new RandomAccessFile(new File("thread" + threads + ".txt"), "rw");
  }

  @Override
  public void run() {
    for (int i = start; i < end; i++) {
      IQuery query = queries.get(i);
      int queryId = query.getQueryId();
      int parameter = query.getParameter();
      try {
        IRecord record = databasePool.read(queryId, parameter);
        record.writeToFile(randomAccessFile);
      } catch (IOException ioe) {
        System.out.println("Something went wrong! : " + ioe.getMessage());
        ioe.printStackTrace();
      }
    }
  }
}
