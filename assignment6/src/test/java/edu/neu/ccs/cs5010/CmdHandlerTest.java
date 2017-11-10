package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CmdHandlerTest {

  private ICmdHandler cmdHandler1;
  private ICmdHandler cmdHandler2;
  private ICmdHandler cmdHandler3;

  @Before
  public void setUp() throws Exception {
    String[] args1 = {"nodes_small.csv", "edges_small.csv", "output.csv"};
    cmdHandler1 = new CmdHandler(args1);

    String[] args2 = {"nodes_10000.csv", "edges_small.csv", "output.csv",
    "--processing-flag", "e", "--number-of-users-to-process", "50"};
    cmdHandler2 = new CmdHandler(args2);

    String[] args3 = {"nodes_10000.csv", "edges_10000.csv", "output.csv",
        "--processing-flag", "r", "--number-of-users-to-process", "30",
        "--number-of-recommendations", "10"};
    cmdHandler3 = new CmdHandler(args3);
  }

  @Test
  public void getNodeFile() throws Exception {
    assertTrue(cmdHandler1.getNodeFile().equals("nodes_small.csv"));
    assertTrue(cmdHandler2.getNodeFile().equals("nodes_10000.csv"));
    assertTrue(cmdHandler3.getNodeFile().equals("nodes_10000.csv"));
  }

  @Test
  public void getEdgeFile() throws Exception {
    assertTrue(cmdHandler1.getEdgeFile().equals("edges_small.csv"));
    assertTrue(cmdHandler2.getEdgeFile().equals("edges_small.csv"));
    assertTrue(cmdHandler3.getEdgeFile().equals("edges_10000.csv"));
  }

  @Test
  public void getOutputFile() throws Exception {
    assertTrue(cmdHandler1.getOutputFile().equals("output.csv"));
    assertTrue(cmdHandler2.getOutputFile().equals("output.csv"));
    assertTrue(cmdHandler3.getOutputFile().equals("output.csv"));
  }

  @Test
  public void getProcessingFlag() throws Exception {
    assertTrue(cmdHandler1.getProcessingFlag() == 's');
    assertTrue(cmdHandler2.getProcessingFlag() == 'e');
    assertTrue(cmdHandler3.getProcessingFlag() == 'r');
  }

  @Test
  public void getNumUsersToProcess() throws Exception {
    assertTrue(cmdHandler1.getNumUsersToProcess() == 100);
    assertTrue(cmdHandler2.getNumUsersToProcess() == 50);
    assertTrue(cmdHandler3.getNumUsersToProcess() == 30);
  }

  @Test
  public void getNumRecommendations() throws Exception {
    assertTrue(cmdHandler1.getNumRecommendations() == 15);
    assertTrue(cmdHandler2.getNumRecommendations() == 15);
    assertTrue(cmdHandler3.getNumRecommendations() == 10);
  }

  @Test
  public void isValid() throws Exception {
    assertTrue(cmdHandler1.isValid());
    assertTrue(cmdHandler2.isValid());
    assertTrue(cmdHandler3.isValid());
  }

  @Test
  public void getErrorMessage() throws Exception {
    assertTrue(cmdHandler1.getErrorMessage().equals(""));
    assertTrue(cmdHandler2.getErrorMessage().equals(""));
    assertTrue(cmdHandler3.getErrorMessage().equals(""));
  }

  @Test
  public void equals() throws Exception {
  }

  @Test
  public void testHashCode() throws Exception {
  }

}