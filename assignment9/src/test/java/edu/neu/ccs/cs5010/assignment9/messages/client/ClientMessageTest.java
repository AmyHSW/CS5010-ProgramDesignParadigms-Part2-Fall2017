package edu.neu.ccs.cs5010.assignment9.messages.client;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientMessageTest {

  private IMessage message1;
  private IMessage message2;

  @Before
  public void setUp() throws Exception {
    message1 = new ClientMessage("", "");
    message2 = new ClientMessage("KEEP_DICE", "1 2 3 4 5 1 1 1 1 1");
  }

  @Test
  public void testToString() throws Exception {
    assertEquals(message1.toString(), "");
    assertEquals(message2.toString(), "KEEP_DICE:1 2 3 4 5 1 1 1 1 1");
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!message1.equals(null));
    assertTrue(message1.equals(message1));
    assertTrue(!message1.equals(""));
    assertTrue(!message1.equals(message2));
    assertTrue(message1.equals(new ClientMessage("", "")));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(message1.hashCode() != message2.hashCode());
  }

}