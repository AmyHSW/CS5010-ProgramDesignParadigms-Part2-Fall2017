package edu.neu.ccs.cs5010.assignment8.cmdhandler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataCmdHandlerTest {
  private ICmdHandler handler1;
  private ICmdHandler handler2;
  private ICmdHandler handler3;
  private ICmdHandler handler4;
  private ICmdHandler handler5;

  @Before
  public void setUp() throws Exception {
    String[] args1 = {"PDPAssignment.csv"};
    handler1 = new DataCmdHandler(args1);
    handler3 = new DataCmdHandler(args1);
    String[] args2 = {"PDPAssignment"};
    handler2 = new DataCmdHandler(args2);
    String[] args4 = {"PDPAssignment1.csv"};
    handler4 = new DataCmdHandler(args4);
    String[] args5 = {"PDPAssignment1.csv", "20"};
    handler5 = new DataCmdHandler(args5);
  }

  @Test
  public void isValid() throws Exception {
    assertTrue(handler1.isValid());
    assertTrue(!handler2.isValid());
    assertTrue(handler3.isValid());
    assertTrue(handler4.isValid());
    assertTrue(!handler5.isValid());
  }

  @Test
  public void getTestFilename() throws Exception {
    assertTrue(handler1.getTestFilename().equals("PDPAssignment.csv"));
  }

  @Test
  public void getErrorMessage() throws Exception {
    assertTrue(handler1.getErrorMessage().equals(""));
  }

  @Test
  public void equals() throws Exception {
    assertTrue(handler1.equals(handler1));
    assertTrue(!handler1.equals(null));
    assertTrue(!handler1.equals(""));
    assertTrue(!handler1.equals(handler4));
    assertTrue(handler1.equals(handler3));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(handler1.hashCode() == handler3.hashCode());
  }

}