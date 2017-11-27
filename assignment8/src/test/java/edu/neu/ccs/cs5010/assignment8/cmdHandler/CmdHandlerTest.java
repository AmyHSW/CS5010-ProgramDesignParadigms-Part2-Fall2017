package edu.neu.ccs.cs5010.assignment8.cmdHandler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CmdHandlerTest {

  private ICmdHandler handler1;
  private ICmdHandler handler2;
  private ICmdHandler handler3;
  private ICmdHandler handler4;
  private ICmdHandler handler5;
  private ICmdHandler handler6;
  private ICmdHandler handler7;

  @Before
  public void setUp() throws Exception {
    String[] args1 = {"PDPAssignment8.csv", "20"};
    handler1 = new CmdHandler(args1);

    String[] args2 = {"PDPAssignment8.csv"};
    handler2 = new CmdHandler(args2);

    String[] args3 = {"PDPAssignment8", "20"};
    handler3 = new CmdHandler(args3);

    String[] args4 = {"PDPAssignment8.csv", "-3"};
    handler4 = new CmdHandler(args4);

    String[] args5 = {"PDPAssignment8.csv", "30"};
    handler5 = new CmdHandler(args5);

    handler6 = new CmdHandler(args1);

    String[] args7 = {"PDPAssignment8.csv", "200"};
    handler7 = new CmdHandler(args7);
  }

  @Test
  public void isValid() throws Exception {
    assertTrue(handler1.isValid());
    assertTrue(!handler2.isValid());
    assertTrue(!handler3.isValid());
    assertTrue(!handler4.isValid());
    assertTrue(!handler5.isValid());
  }

  @Test
  public void getTestFilename() throws Exception {
    assertTrue(handler1.getTestFilename().equals("PDPAssignment8.csv"));
  }

  @Test
  public void getNumQueries() throws Exception {
    assertTrue(handler1.getNumQueries() == 20);
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
    assertTrue(!handler1.equals(handler5));
    assertTrue(handler1.equals(handler6));
    assertTrue(!handler7.equals(handler1));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(handler1.hashCode() == handler6.hashCode());
  }

}