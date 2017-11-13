package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class EdgeGeneratorTest {
  private IEdgeGenerator edgeGenerator1;
  private IEdgeGenerator edgeGenerator2;
  private IEdgeGenerator edgeGenerator3;
  private IEdgeGenerator edgeGenerator4;
  private IEdgeGenerator edgeGenerator5;

  @Before
  public void setUp() throws Exception {
    List<String> lines1 = new ArrayList<>();
    lines1.add("Source,Destination");
    lines1.add("1,39");
    List<String> lines2 = new ArrayList<>(lines1);
    lines2.add("2,33");
    ICsvParser csvParser1 = new CsvParser(lines1);
    ICsvParser csvParser2 = new CsvParser(lines2);
    edgeGenerator1 = new EdgeGenerator(csvParser1);
    edgeGenerator2 = new EdgeGenerator(csvParser1);
    edgeGenerator3 = new EdgeGenerator(csvParser2);
    edgeGenerator4 = new EdgeGenerator(null);
    edgeGenerator5 = new EdgeGenerator(null);
  }

  @Test(expected = NoSuchElementException.class)
  public void expectedNoSuchElementException() throws Exception {
    edgeGenerator1.getNextEdge();
    edgeGenerator1.getNextEdge();
  }

  @Test
  public void testEquals() throws Exception {
    assertTrue("EdgeGenerator failed to provide the correct equal.",
            edgeGenerator1.equals(edgeGenerator1));
    assertFalse("EdgeGenerator failed to provide the correct equal.",
            edgeGenerator1.equals(null));
    assertFalse("EdgeGenerator failed to provide the correct equal.",
            edgeGenerator1.equals("1"));
    assertFalse("EdgeGenerator failed to provide the correct equal.",
            edgeGenerator1.equals(edgeGenerator3));
    assertFalse("EdgeGenerator failed to provide the correct equal.",
            edgeGenerator1.equals(edgeGenerator4));
    assertFalse("EdgeGenerator failed to provide the correct equal.",
            edgeGenerator4.equals(edgeGenerator2));
    assertTrue("EdgeGenerator failed to provide the correct equal.",
            edgeGenerator1.equals(edgeGenerator2));
    assertTrue("EdgeGenerator failed to provide the correct equal.",
            edgeGenerator4.equals(edgeGenerator5));
    assertFalse("EdgeGenerator failed to provide the correct hashcode.",
            edgeGenerator1.hashCode() == edgeGenerator3.hashCode());
    assertFalse("EdgeGenerator failed to provide the correct hashcode.",
            edgeGenerator1.hashCode() == edgeGenerator4.hashCode());
    assertTrue("EdgeGenerator failed to provide the correct hashcode.",
            edgeGenerator1.hashCode() == edgeGenerator2.hashCode());
  }

}