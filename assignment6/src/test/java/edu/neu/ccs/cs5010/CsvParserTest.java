package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CsvParserTest {
  private ICsvParser csvParser1;
  private ICsvParser csvParser2;
  private ICsvParser csvParser3;
  private ICsvParser csvParser4;

  @Before
  public void setUp() throws Exception {
    List<String> lines1 = new ArrayList<>();
    lines1.add("Node ID,Date created,Gender,Age,City");
    lines1.add("1,1/1/09,F,22,Seattle");
    List<String> lines2 = new ArrayList<>(lines1);
    lines2.add("2,1/15/09,F,18,Melbourne");
    csvParser1 = new CsvParser(lines1);
    csvParser2 = new CsvParser(lines2);
    csvParser3 = new CsvParser(null);
    csvParser4 = new CsvParser(null);
  }

  @Test
  public void testEquals() throws Exception {
    assertTrue("CsvParser failed to provide the correct equal.",
            csvParser1.equals(csvParser1));
    assertFalse("CsvParser failed to provide the correct equal.",
            csvParser1.equals(null));
    assertFalse("CsvParser failed to provide the correct equal.",
            csvParser1.equals("1"));
    assertFalse("CsvParser failed to provide the correct equal.",
            csvParser1.equals(csvParser2));
    assertFalse("CsvParser failed to provide the correct equal.",
            csvParser1.equals(csvParser3));
    assertTrue("CsvParser failed to provide the correct equal.",
            csvParser3.equals(csvParser4));
    assertFalse("CsvParser failed to provide the correct hashcode.",
            csvParser1.hashCode() == csvParser2.hashCode());
    assertFalse("CsvParser failed to provide the correct hashcode.",
            csvParser1.hashCode() == csvParser3.hashCode());
    assertTrue("CsvParser failed to provide the correct hashcode.",
            csvParser3.hashCode() == csvParser4.hashCode());
  }

}