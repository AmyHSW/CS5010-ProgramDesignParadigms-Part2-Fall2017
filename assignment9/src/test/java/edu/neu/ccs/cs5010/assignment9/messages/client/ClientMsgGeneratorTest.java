package edu.neu.ccs.cs5010.assignment9.messages.client;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ClientMsgGeneratorTest {
  private IClientMsgGenerator generator;

  @Before
  public void setUp() throws Exception {
    String input = "1 1 1 1 1\n 1 1 1 1 1\nYahtzee\nThreeOfKind\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    generator = new ClientMsgGenerator();
}

  @Test
  public void getClientMsg() throws Exception {
    String serverMsg = "CHOOSE_DICE:1 2 3 4 5";
    assertEquals(generator.getClientMsg(serverMsg).toString(),"KEEP_DICE:1 2 3 4 5 1 1 1 1 1");

    serverMsg = "CHOOSE_SCORE:1 2 3 4 5 Yahtzee FourOfKind ThreeOfKind";
    assertEquals(generator.getClientMsg(serverMsg).toString(),"SCORE_CHOICE:Yahtzee");

    serverMsg = "START_ROUND:1";
    assertEquals(generator.getClientMsg(serverMsg).toString(),"");

    serverMsg = "CHOOSE_SCORE:1 2 3 4 5 Yahtzee ThreeOfKind";
    assertEquals(generator.getClientMsg(serverMsg).toString(),"SCORE_CHOICE:ThreeOfKind");
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!generator.equals(null));
    assertTrue(!generator.equals(""));
    assertTrue(generator.equals(generator));
    assertTrue(!generator.equals(new ClientMsgGenerator()));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(generator.hashCode() != (new ClientMsgGeneratorTest()).hashCode());
  }
}