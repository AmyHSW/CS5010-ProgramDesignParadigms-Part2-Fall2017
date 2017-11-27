package edu.neu.ccs.cs5010.assignment8.queryprocessor;

import edu.neu.ccs.cs5010.assignment8.query.IQuery;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The QueryProcessor class is responsible for processing the queries given a query
 * list.
 *
 * <p>Twenty threads will be created to process the queries. Each thread is responsible for
 * a disjoint segment of the queries. All threads start at the same time, with each thread
 * processing its allocated queries sequentially. The termination of all the threads are
 * synchronized.
 *
 * <p>The results of queries are stored in 20 lists of strings, where each list represents the
 * results of one thread.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class QueryProcessor implements IQueryProcessor {

  private static final int NUM_THREADS = 20;
  static CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS + 1);
  private final List<IQuery> queries;
  private final ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
  private Duration runtime;
  private List<List<String>> outputList;

  /**
   * Constructs a new query processor with the query list.
   * @param queryList a list of queries
   */
  public QueryProcessor(List<IQuery> queryList) {
    queries = queryList;
    initOutputList();
  }

  private void initOutputList() {
    outputList = new ArrayList<>();
    for (int i = 0; i < NUM_THREADS; i++) {
      outputList.add(new ArrayList<>());
    }
  }

  /**
   * Processes the queries in the query list.
   * @throws InterruptedException if the thread is interrupted
   * @throws IOException if there is an I/O failure
   */
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

  /**
   * Returns the results of the queries.
   * @return the results of the queries.
   */
  @Override
  public List<List<String>> getOutputList() {
    return outputList;
  }

  /**
   * Returns the run time of this processor.
   * @return the run time of this processor.
   */
  @Override
  public Duration getRuntime() {
    return runtime;
  }
}
