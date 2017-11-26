package edu.neu.ccs.cs5010.assignment8.queryProcessor;

import java.io.IOException;
import java.time.Duration;

public interface IQueryProcessor {

  void processQueries() throws InterruptedException, IOException;
  Duration getRuntime();
}
