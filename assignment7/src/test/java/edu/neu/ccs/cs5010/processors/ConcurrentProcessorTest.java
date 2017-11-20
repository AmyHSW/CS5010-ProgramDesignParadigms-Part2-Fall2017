package edu.neu.ccs.cs5010.processors;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.lift.ILift;
import edu.neu.ccs.cs5010.lift.Lift;
import edu.neu.ccs.cs5010.skier.ISkier;
import edu.neu.ccs.cs5010.skier.Skier;
import edu.neu.ccs.cs5010.exceptions.InvalidInputDataException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ConcurrentProcessorTest {

  private static final String INPUT = "input_test.csv";
  private IProcessor processor;
  private List<String[]> inputData;

  @Before
  public void setUp() throws Exception {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    inputData = parser.parseAll(new File(INPUT));

    processor = new ConcurrentProcessor(inputData);
    processor.processInput();
  }

  @Test
  public void getSkierVerticalMeters() throws Exception {
    Map<String, ISkier> map = processor.getSkierMap();
    assertTrue(map.get("1").getVerticalMeters() == 5300);
    assertTrue(map.get("9").getVerticalMeters() == 7600);
  }

  @Test
  public void getLiftNumRides() throws Exception {
    List<ILift> list = processor.getLiftList();
    assertTrue(list.get(0).getNumber() == 8);
    assertTrue(list.get(8).getNumber() == 3);
  }

  @Test
  public void getHourRides() throws Exception {
    List<List<ILift>> list = processor.getHourRides();
    assertTrue(list.get(0).get(0).getNumber() == 2);
    assertTrue(list.get(0).get(18).getNumber() == 1);
    assertTrue(list.get(5).get(23).getNumber() == 2);
    assertTrue(list.get(0).get(26).getNumber() == 2);
    assertTrue(list.get(1).get(26).getNumber() == 1);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!processor.equals(null));
    assertTrue(processor.equals(processor));
    assertTrue(!processor.equals(""));
    assertTrue(processor.equals(new ConcurrentProcessor(inputData)));
    assertTrue(!processor.equals(
        new ConcurrentProcessor(inputData.subList(1, inputData.size()))));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(processor.hashCode() == (new ConcurrentProcessor(inputData)).hashCode());
  }

  @Test(expected = InvalidInputDataException.class)
  public void expectedInvalidInputDataExceptionNull() throws Exception {
    new ConcurrentProcessor(null);
  }

  @Test(expected = InvalidInputDataException.class)
  public void expectedInvalidInputDataExceptionSizeOne() throws Exception {
    List<String[]> list = new ArrayList<>();
    list.add(new String[] {""});
    new ConcurrentProcessor(list);
  }
}
