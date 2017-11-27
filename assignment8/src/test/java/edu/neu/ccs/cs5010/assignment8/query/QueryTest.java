package edu.neu.ccs.cs5010.assignment8.query;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueryTest {

  private IQuery query;

  @Before
  public void setUp() throws Exception {
    query = new Query(1, 1);
  }

  @Test
  public void getQueryId() throws Exception {
    assertTrue(query.getQueryId() == 1);
  }

  @Test
  public void getParameter() throws Exception {
    assertTrue(query.getParameter() == 1);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!query.equals(null));
    assertTrue(!query.equals(""));
    assertTrue(query.equals(query));
    assertTrue(!query.equals(new Query(2, 1)));
    assertTrue(!query.equals(new Query(1, 2)));
    assertTrue(query.equals(new Query(1, 1)));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(query.hashCode() == (new Query(1, 1)).hashCode());
  }

}