package edu.neu.ccs.cs5010.assignment8;

import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputArgumentException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkiProcessorTest {

  @Test
  public void main() throws Exception {
    SkiDataProcessor.main(new String[0]);
    String[] args = {"PDPAssignment8.csv", "40000"};
    SkiQueryProcessor.main(args);
  }

  @Test(expected = InvalidInputArgumentException.class)
  public void expectedInvalidInputArgumentException() throws Exception {
    String[] args = {"PDPAssignment"};
    SkiQueryProcessor.main(args);
  }
}