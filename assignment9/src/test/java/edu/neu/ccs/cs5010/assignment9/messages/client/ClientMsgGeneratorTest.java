package edu.neu.ccs.cs5010.assignment9.messages.client;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ClientMsgGeneratorTest {
  private IClientMsgGenerator generator1;
  private IClientMsgGenerator generator2;
  private IClientMsgGenerator generator3;
  private IClientMsgGenerator generator4;
  private IClientMsgGenerator generator5;

  @Before
  public void setUp() throws Exception {
    String server1 = "CHOOSE_DICE:1 2 3 4 5";
    System.setIn(new ByteArrayInputStream("1 1 1 1 1".getBytes()));
    generator1 = new ClientMsgGenerator(server1);
    System.setIn(new ByteArrayInputStream("1 1 1 1 1".getBytes()));
    generator3 = new ClientMsgGenerator(server1);
    String server2 = "CHOOSE_SCORE:1 2 3 4 5 Yahtzee FourOfKind ThreeOfKind";
    System.setIn(new ByteArrayInputStream("Yahtzee".getBytes()));
    generator2 = new ClientMsgGenerator(server2);
    generator4 = new ClientMsgGenerator("START_ROUND:1");
    String server3 = "CHOOSE_SCORE:1 2 3 4 5 Yahtzee ThreeOfKind";
    System.setIn(new ByteArrayInputStream("ThreeOfKind".getBytes()));
    generator5 = new ClientMsgGenerator(server3);
}

  @Test
  public void getClientMsg() throws Exception {
    assertEquals(generator1.getClientMsg(), "KEEP_DICE:1 2 3 4 5 1 1 1 1 1");
    assertEquals(generator2.getClientMsg(), "SCORE_CHOICE:Yahtzee");
    assertEquals(generator4.getClientMsg(), "");
    assertEquals(generator5.getClientMsg(), "SCORE_CHOICE:ThreeOfKind");
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!generator1.equals(null));
    assertTrue(!generator1.equals(""));
    assertTrue(generator1.equals(generator1));
    assertTrue(generator1.equals(generator3));
    assertTrue(!generator1.equals(generator2));
    assertTrue(!generator1.equals(generator4));
    assertTrue(!generator2.equals(generator5));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(generator1.hashCode() == generator3.hashCode());
    assertTrue(generator1.hashCode() != generator2.hashCode());
    assertTrue(generator1.hashCode() != generator4.hashCode());
    assertTrue(generator2.hashCode() != generator5.hashCode());
  }
}