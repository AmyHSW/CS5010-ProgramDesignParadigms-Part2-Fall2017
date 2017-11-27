package edu.neu.ccs.cs5010.assignment8.reader;

import edu.neu.ccs.cs5010.assignment8.exception.InvalidQueryIdException;
import org.junit.Test;

import static org.junit.Assert.*;

public class IReaderTest {

  @Test(expected = InvalidQueryIdException.class)
  public void expectedInvalidQueryIdException() throws Exception {
    IReader.getReader(5, 5);
  }

}