package edu.neu.ccs.cs5010.assignment8.queryprocessor;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public interface IQueryProcessor {

  void processQueries() throws InterruptedException, IOException;

  Duration getRuntime();

  List<List<String>> getOutputList();
}
