package edu.neu.ccs.cs5010.assignment9.messages.server;

import edu.neu.ccs.cs5010.assignment9.messages.client.IClientMsgGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerMessageParserTest {
  private IMessageParser parser1;
  private IMessageParser parser2;
  private IMessageParser parser3;
  private IMessageParser parser4;

  @Before
  public void setUp() throws Exception {
    String server1 = "CHOOSE_DICE:1 2 3 4 5";
    parser1 = new ServerMessageParser(server1);
    parser3 = new ServerMessageParser(server1);
    String server2 = "CHOOSE_SCORE:1 2 3 4 5 Yahtzee FourOfKind ThreeOfKind";
    parser2 = new ServerMessageParser(server2);
    String server3 = "CHOOSE_SCORE:1 2 3 4 5 Yahtzee ThreeOfKind";
    parser4 = new ServerMessageParser(server3);
  }

  @Test
  public void getFrame() throws Exception {
    assertEquals(parser1.getFrame(), "CHOOSE_DICE");
    assertEquals(parser3.getFrame(), "CHOOSE_DICE");
    assertEquals(parser2.getFrame(), "CHOOSE_SCORE");
    assertEquals(parser4.getFrame(), "CHOOSE_SCORE");
  }

  @Test
  public void getPayload() throws Exception {
    assertEquals(parser1.getPayload(), "1 2 3 4 5");
    assertEquals(parser3.getPayload(), "1 2 3 4 5");
    assertEquals(parser2.getPayload(), "1 2 3 4 5 Yahtzee FourOfKind ThreeOfKind");
    assertEquals(parser4.getPayload(), "1 2 3 4 5 Yahtzee ThreeOfKind");
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!parser1.equals(null));
    assertTrue(!parser1.equals(""));
    assertTrue(parser1.equals(parser1));
    assertTrue(parser1.equals(parser3));
    assertTrue(!parser1.equals(parser2));
    assertTrue(!parser2.equals(parser4));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(parser1.hashCode() == parser3.hashCode());
    assertTrue(parser1.hashCode() != parser2.hashCode());
    assertTrue(parser2.hashCode() != parser4.hashCode());
  }
}