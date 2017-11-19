package edu.neu.ccs.cs5010.processors;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.exceptions.InvalidInputDataException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SequentialProcessorTest {

  private static final String INPUT = "input_test.csv";
  private List<String[]> inputData;
  private IProcessor processor;

  @Before
  public void setUp() throws Exception {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    inputData = parser.parseAll(new File(INPUT));

    processor = new SequentialProcessor(inputData);
    processor.processInput();
  }

  @Test
  public void getSkierVerticalMeters() throws Exception {
    Map<String, Integer> map = processor.getSkierVerticalMeters();
    assertTrue(map.get("1") == 5300);
    assertTrue(map.get("9") == 7600);
  }

  @Test
  public void getLiftNumRides() throws Exception {
    Map<String, Integer> map = processor.getLiftNumRides();
    assertTrue(map.get("1") == 8);
    assertTrue(map.get("9") == 3);
  }

  @Test
  public void getHourRides() throws Exception {
    List<Map<String, Integer>> list = processor.getHourRides();
    assertTrue(list.get(0).get("1") == 2);
    assertTrue(list.get(0).get("19") == 1);
    assertTrue(list.get(5).get("24") == 2);
    assertTrue(list.get(1).get("27") == 1);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(processor.equals(processor));
    assertTrue(!processor.equals(null));
    assertTrue(!processor.equals(""));
    assertTrue(processor.equals(new SequentialProcessor(inputData)));
    assertTrue(!processor.equals(
        new SequentialProcessor(inputData.subList(1, inputData.size()))));
  }

  @Test(expected = InvalidInputDataException.class)
  public void expectedInvalidInputDataExceptionNull() throws Exception {
    new SequentialProcessor(null);
  }

  @Test(expected = InvalidInputDataException.class)
  public void expectedInvalidInputDataExceptionSizeOne() throws Exception {
    List<String[]> list = new ArrayList<>();
    list.add(new String[] {""});
    new SequentialProcessor(list);
  }
}