package edu.neu.ccs.cs5010.assignment8.record;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HourRecordTest {

  private IRecord hourRecord;

  @Before
  public void setUp() throws Exception {
    hourRecord = new HourRecord();
  }

  @Test
  public void getParameter() throws Exception {
    assertTrue(hourRecord.getParameter() == 0);
  }

  @Test
  public void toIndex() throws Exception {
    assertTrue(HourRecord.toIndex(20) == 0);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!hourRecord.equals(null));
    assertTrue(hourRecord.equals(hourRecord));
    assertTrue(!hourRecord.equals(""));
    assertTrue(hourRecord.equals(new HourRecord()));
    assertTrue(!hourRecord.equals(new HourRecord(1, new ArrayList<>())));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(hourRecord.hashCode() == (new HourRecord()).hashCode());
  }

}