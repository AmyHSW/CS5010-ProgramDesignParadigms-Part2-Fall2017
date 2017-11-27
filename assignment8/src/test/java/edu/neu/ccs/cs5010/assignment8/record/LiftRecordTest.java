package edu.neu.ccs.cs5010.assignment8.record;

import edu.neu.ccs.cs5010.assignment8.exception.InvalidLiftIdException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LiftRecordTest {

  private IRecord liftRecord;

  @Before
  public void setUp() throws Exception {
    liftRecord = new LiftRecord(1);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!liftRecord.equals(null));
    assertTrue(!liftRecord.equals(""));
    assertTrue(liftRecord.equals(liftRecord));
    assertTrue(!liftRecord.equals(new LiftRecord()));
    assertTrue(liftRecord.equals(new LiftRecord(1)));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(liftRecord.hashCode() == (new LiftRecord(1)).hashCode());
  }

  @Test(expected = InvalidLiftIdException.class)
  public void expectedInvalidLiftIdException() throws Exception {
    new LiftRecord(41);
  }

  @Test(expected = InvalidLiftIdException.class)
  public void expectedInvalidLiftIdException2() throws Exception {
    new LiftRecord(0);
  }
}