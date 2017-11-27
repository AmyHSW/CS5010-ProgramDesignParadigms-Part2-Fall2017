package edu.neu.ccs.cs5010.assignment8.record;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkierRecordTest {

  private IRecord skierRecord;

  @Before
  public void setUp() throws Exception {
    skierRecord = new SkierRecord(1);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!skierRecord.equals(null));
    assertTrue(!skierRecord.equals(""));
    assertTrue(skierRecord.equals(skierRecord));
    assertTrue(skierRecord.equals(new SkierRecord(1)));
    assertTrue(!skierRecord.equals(new SkierRecord(2)));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(skierRecord.hashCode() == (new SkierRecord(1)).hashCode());
  }

}