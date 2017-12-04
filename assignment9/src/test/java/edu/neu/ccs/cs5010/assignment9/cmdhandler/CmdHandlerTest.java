package edu.neu.ccs.cs5010.assignment9.cmdhandler;

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

  @Before
  public void setUp() throws Exception {
    String[] args1 = {"localhost", "1200"};
    handler1 = new CmdHandler(args1);
    handler3 = new CmdHandler(args1);
    String[] args2 = {"localhost"};
    handler2 = new CmdHandler(args2);
    String[] args4 = {"localhost", "100"};
    handler4 = new CmdHandler(args4);
    String[] args5 = {"localhost", "-1"};
    handler5 = new CmdHandler(args5);
    String[] args6 = {"127.1.0.0", "1200"};
    handler6 = new CmdHandler(args6);
  }

  @Test
  public void isValid() throws Exception {
    assertTrue(handler1.isValid());
    assertTrue(!handler2.isValid());
    assertTrue(handler3.isValid());
    assertTrue(handler4.isValid());
    assertTrue(!handler5.isValid());
    assertTrue(handler6.isValid());
  }

  @Test
  public void getHostname() throws Exception {
    assertTrue(handler1.getHostname().equals("localhost"));
  }

  @Test
  public void getPortNumber() throws Exception {
    assertTrue(handler1.getPortNumber() == 1200);
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
    assertTrue(!handler1.equals(handler6));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(handler1.hashCode() == handler3.hashCode());
  }
}