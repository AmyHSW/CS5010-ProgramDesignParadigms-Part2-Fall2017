package edu.neu.ccs.cs5010.pairs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

  private static final String FIRST = "first";
  private static final String LAST = "last";

  private IPair pair;

  @Before
  public void setUp() throws Exception {
    pair = new Pair(FIRST, LAST);
  }

  @Test
  public void getFirst() throws Exception {
    assertTrue(pair.getFirst().equals(FIRST));
  }

  @Test
  public void getLast() throws Exception {
    assertTrue(pair.getLast().equals(LAST));
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!pair.equals(null));
    assertTrue(pair.equals(pair));
    assertTrue(!pair.equals(""));
    assertTrue(!pair.equals(new Pair("", LAST)));
    assertTrue(!pair.equals(new Pair(FIRST, "")));
    assertTrue(pair.equals(new Pair(FIRST, LAST)));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(pair.hashCode() == (new Pair(FIRST, LAST)).hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException1() throws Exception {
    new Pair(null, LAST);
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException2() throws Exception {
    new Pair(FIRST, null);
  }
}