package edu.neu.ccs.cs5010.processors;

import edu.neu.ccs.cs5010.consumers.HourQueueConsumer;
import edu.neu.ccs.cs5010.consumers.LiftQueueConsumer;
import edu.neu.ccs.cs5010.consumers.SkierQueueConsumer;
import edu.neu.ccs.cs5010.exceptions.InvalidInputDataException;
import edu.neu.ccs.cs5010.pairs.IPair;
import edu.neu.ccs.cs5010.producers.HourQueueProducer;
import edu.neu.ccs.cs5010.producers.LiftQueueProducer;
import edu.neu.ccs.cs5010.producers.SkierQueueProducer;
import edu.neu.ccs.cs5010.skier.ISkier;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ConcurrentProcessor extends Processor {

  private static final int NUM_THREADS = 1;
  private static final int NUM_SECONDS_WAIT = 5;

  private final ExecutorService executorService = Executors.newCachedThreadPool();

  private final List<String[]> inputData;
  private final BlockingQueue<IPair> skierQueue;
  private final BlockingQueue<String> liftQueue;
  private final BlockingQueue<IPair> hourQueue;
  private final ConcurrentMap<String, ISkier> skierMap;

  public ConcurrentProcessor(List<String[]> inputData) {
    super();
    validate(inputData);
    this.inputData = inputData.subList(1, inputData.size());
    skierQueue = new LinkedBlockingDeque<>();
    liftQueue = new LinkedBlockingDeque<>();
    hourQueue = new LinkedBlockingDeque<>();
    skierMap = new ConcurrentHashMap<>();
  }

  private void validate(List<String[]> input) {
    if (input == null || input.size() <= 1) {
      throw new InvalidInputDataException("Input data doesn't contain enough information.");
    }
  }

  public void processInput() throws InterruptedException {
    final long startTime = System.currentTimeMillis();

    executorService.execute(new SkierQueueProducer(inputData, skierQueue));
    executorService.execute(new LiftQueueProducer(inputData, liftQueue));
    executorService.execute(new HourQueueProducer(inputData, hourQueue));

    for (int i = 0; i < NUM_THREADS; i++) {
      executorService.execute(new SkierQueueConsumer(
          skierQueue, skierMap));
      executorService.execute(new LiftQueueConsumer(liftQueue, liftList));
      executorService.execute(new HourQueueConsumer(hourQueue, hourRides));
    }

    executorService.shutdown();
    executorService.awaitTermination(NUM_SECONDS_WAIT, TimeUnit.SECONDS);

    runTime = Duration.ofMillis(System.currentTimeMillis() - startTime);
  }

  @Override
  public Map<String, ISkier> getSkierMap() {
    return skierMap;
  }

  @Override
  public String toString() {
    return "Concurrent processor";
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    ConcurrentProcessor that = (ConcurrentProcessor) other;

    return inputData.equals(that.inputData);
  }

  @Override
  public int hashCode() {
    return inputData.hashCode();
  }

}
