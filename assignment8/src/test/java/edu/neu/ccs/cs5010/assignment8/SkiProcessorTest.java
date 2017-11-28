package edu.neu.ccs.cs5010.assignment8;

import edu.neu.ccs.cs5010.assignment8.exception.InvalidInputArgumentException;
import org.junit.Test;

public class SkiProcessorTest {

  @Test
  public void main() throws Exception {
    String[] args1 = {"PDPAssignment.csv"};
    SkiDataProcessor.main(args1);
    String[] args2 = {"PDPAssignment8.csv", "40000"};
    SkiQueryProcessor.main(args2);
  }

  @Test(expected = InvalidInputArgumentException.class)
  public void expectedInvalidInputArgumentException1() throws Exception {
    String[] args = {"PDPAssignment"};
    SkiDataProcessor.main(args);
  }

  @Test(expected = InvalidInputArgumentException.class)
  public void expectedInvalidInputArgumentException2() throws Exception {
    String[] args = {"PDPAssignment"};
    SkiQueryProcessor.main(args);
  }
}