package edu.neu.ccs.cs5010.assignment8.record;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkierRowRecordTest {

  private IRecord record;

  @Before
  public void setUp() throws Exception {
    record = new SkierRowRecord(1, 1);
  }

  @Test
  public void getParameter() throws Exception {
  }

  @Test
  public void getRowIndex() throws Exception {
  }

  @Test
  public void readFromFile() throws Exception {
  }

  @Test
  public void writeToFile() throws Exception {
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!record.equals(null));
    assertTrue(!record.equals(""));
    assertTrue(record.equals(record));
    assertTrue(!record.equals(new SkierRowRecord()));
    assertTrue(record.equals(new SkierRowRecord(1, 1)));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(record.hashCode() == (new SkierRowRecord(1, 1)).hashCode());
  }

}