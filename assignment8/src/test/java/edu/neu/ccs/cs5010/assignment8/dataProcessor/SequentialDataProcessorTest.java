package edu.neu.ccs.cs5010.assignment8.dataProcessor;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.neu.ccs.cs5010.assignment8.exceptions.InvalidInputDataException;
import edu.neu.ccs.cs5010.assignment8.record.IRecord;
import edu.neu.ccs.cs5010.assignment8.record.LiftRecord;
import edu.neu.ccs.cs5010.assignment8.record.SkierRecord;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SequentialDataProcessorTest {

  private static final String INPUT = "input_test.csv";
  private List<String[]> inputData;
  private IDataProcessor processor;

  @Before
  public void setUp() throws Exception {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    inputData = parser.parseAll(new File(INPUT));

    processor = new SequentialDataProcessor(inputData);
    processor.processInput();
  }

  @Test
  public void getSkierList() throws Exception {
    List<IRecord> skiers = processor.getSkierList();
    assertTrue(((SkierRecord)skiers.get(0)).getTotalVertical() == 5300);
    assertTrue(((SkierRecord)skiers.get(8)).getTotalVertical() == 7600);
  }

  @Test
  public void getLiftNumRides() throws Exception {
    List<IRecord> list = processor.getLiftList();
    assertTrue(((LiftRecord)list.get(0)).getNumber() == 8);
    assertTrue(((LiftRecord)list.get(8)).getNumber() == 3);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(processor.equals(processor));
    assertTrue(!processor.equals(null));
    assertTrue(!processor.equals(""));
    assertTrue(processor.equals(new SequentialDataProcessor(inputData)));
    assertTrue(!processor.equals(
        new SequentialDataProcessor(inputData.subList(1, inputData.size()))));
  }

  @Test(expected = InvalidInputDataException.class)
  public void expectedInvalidInputDataExceptionNull() throws Exception {
    new SequentialDataProcessor(null);
  }

  @Test(expected = InvalidInputDataException.class)
  public void expectedInvalidInputDataExceptionSizeOne() throws Exception {
    List<String[]> list = new ArrayList<>();
    list.add(new String[] {""});
    new SequentialDataProcessor(list);
  }

}