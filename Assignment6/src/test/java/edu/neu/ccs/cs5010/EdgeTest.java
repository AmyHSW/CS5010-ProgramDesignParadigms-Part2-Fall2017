package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {

  private IEdge edge;

  @Before
  public void setUp() throws Exception {
    edge = new Edge(1, 2);
  }

  @Test
  public void getFromId() throws Exception {
    assertTrue(edge.getFromId() == 1);
  }

  @Test
  public void getToId() throws Exception {
    assertTrue(edge.getToId() == 2);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!edge.equals(null));
    assertTrue(edge.equals(edge));
    assertTrue(!edge.equals(""));
    assertTrue(!edge.equals(new Edge(3, 2)));
    assertTrue(!edge.equals(new Edge(1,3)));
    assertTrue(edge.equals(new Edge(1, 2)));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(edge.hashCode() == (new Edge(1, 2)).hashCode());
  }

}