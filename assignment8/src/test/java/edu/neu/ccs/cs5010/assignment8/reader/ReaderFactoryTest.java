package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.exception.InvalidQueryIdException;
import org.junit.Test;

public class ReaderFactoryTest {

  @Test(expected = InvalidQueryIdException.class)
  public void expectedInvalidQueryIdException() throws Exception {
    ReaderFactory.getReader(5, 5);
  }

}