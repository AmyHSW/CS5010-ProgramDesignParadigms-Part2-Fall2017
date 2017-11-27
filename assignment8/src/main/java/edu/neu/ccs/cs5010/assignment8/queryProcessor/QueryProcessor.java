package edu.neu.ccs.cs5010.assignment8.queryProcessor;

import edu.neu.ccs.cs5010.assignment8.query.IQuery;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueryProcessor implements IQueryProcessor {

  static CyclicBarrier barrier;
  private static final int NUM_THREADS = 20;
  private final List<IQuery> queries;
  private final ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
  private Duration runtime;
  private List<List<String>> outputList;

  public QueryProcessor(List<IQuery> queryList) {
    queries = queryList;
    initOutputList();
    barrier = new CyclicBarrier(NUM_THREADS + 1);
  }

  private void initOutputList() {
    outputList = new ArrayList<>();
    for (int i = 0; i < NUM_THREADS; i++) {
      outputList.add(new ArrayList<>());
    }
  }

  @Override
  public void processQueries() throws InterruptedException, IOException {
    final long start = System.currentTimeMillis();
    int numQueriesEachThread = queries.size() / NUM_THREADS;
    try {
      for (int i = 0; i < NUM_THREADS; i++) {
        executorService.execute(new QueryThread(
            queries,
            i * numQueriesEachThread,
            (i + 1) * numQueriesEachThread,
            outputList.get(i)));
      }
      barrier.await();
      barrier.await();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
    executorService.shutdown();
    runtime = Duration.ofMillis(System.currentTimeMillis() - start);
  }

  @Override
  public List<List<String>> getOutputList() {
    return outputList;
  }

  @Override
  public Duration getRuntime() {
    return runtime;
  }
}
