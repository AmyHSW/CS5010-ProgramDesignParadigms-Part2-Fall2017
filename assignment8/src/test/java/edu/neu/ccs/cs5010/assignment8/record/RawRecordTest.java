package edu.neu.ccs.cs5010.assignment8.record;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RawRecordTest {

  private IRecord rawRecord;

  @Before
  public void setUp() throws Exception {
    rawRecord = new RawRecord(1, 1, 1);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!rawRecord.equals(null));
    assertTrue(!rawRecord.equals(""));
    assertTrue(rawRecord.equals(rawRecord));
    assertTrue(!rawRecord.equals(new RawRecord()));
    assertTrue(!rawRecord.equals(new RawRecord(1, 2, 2)));
    assertTrue(!rawRecord.equals(new RawRecord(1, 1, 2)));
    assertTrue(rawRecord.equals(new RawRecord(1, 1, 1)));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(rawRecord.hashCode() == (new RawRecord(1, 1, 1)).hashCode());
  }


}