package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CmdHandlerTest {

  private ICmdHandler cmdHandler1;
  private ICmdHandler cmdHandler2;
  private ICmdHandler cmdHandler3;
  private ICmdHandler cmdHandler4;
  private ICmdHandler cmdHandler5;
  private ICmdHandler cmdHandler6;
  private ICmdHandler cmdHandler7;
  private ICmdHandler cmdHandler8;
  private ICmdHandler cmdHandler9;
  private ICmdHandler cmdHandler10;
  private ICmdHandler cmdHandler11;
  private ICmdHandler cmdHandler12;
  private ICmdHandler cmdHandler13;
  private ICmdHandler cmdHandler14;

  @Before
  public void setUp() throws Exception {
    String[] args1 = {"nodes_small.csv", "edges_small.csv", "output.csv"};
    cmdHandler1 = new CmdHandler(args1);

    String[] args2 = {"nodes_10000.csv", "edges_small.csv", "output.csv",
    "--processing-flag", "es", "--number-of-users-to-process", "50"};
    cmdHandler2 = new CmdHandler(args2);

    String[] args3 = {"nodes_10000.csv", "edges_10000.csv", "output.csv",
        "--processing-flag", "r", "--number-of-users-to-process", "30",
        "--number-of-recommendations", "10"};
    cmdHandler3 = new CmdHandler(args3);

    String[] args4 = {"nodes_small.csv", "edges_small.csv"};
    cmdHandler4 = new CmdHandler(args4);

    String[] args5 = {"nodes_small.csv", "edges_small.csv", "output.csv",
        "--processing-flag", "e", "--number-of-users-to-process", "a"};
    cmdHandler5 = new CmdHandler(args5);

    String[] args6 = {"nodes_small.csv", "edges_small.csv", "output.csv",
        "--processing-flag", "e", "--number-of-users-to-process", "200"};
    cmdHandler6 = new CmdHandler(args6);

    String[] args7 = {"nodes_10000.csv", "edges_small.csv", "output.csv",
        "--processing-flag", "e", "--number-of-users-to-process", "20000"};
    cmdHandler7 = new CmdHandler(args7);

    String[] args8 = {"nodes_200.csv", "edges_small.csv", "output.csv",
        "--processing-flag", "e", "--number-of-users-to-process", "50"};
    cmdHandler8 = new CmdHandler(args8);

    String[] args9 = {"nodes_small.csv", "edges_small.csv", "output.csv",
        "--processing-flag"};
    cmdHandler9 = new CmdHandler(args9);

    String[] args10 = {"nodes_small.csv", "edges_small.csv", "output.csv",
        "--processing", "s"};
    cmdHandler10 = new CmdHandler(args10);

    String[] args11 = {"nodes_10000.csv", "edges_10000.csv", "output.csv",
        "--processing-flag", "r", "--number-of-users-to-process", "30",
        "--number-of-recommendations", "200"};
    cmdHandler11 = new CmdHandler(args11);

    String[] args12 = {"nodes_small", "edges_small.csv", "output.csv"};
    cmdHandler12 = new CmdHandler(args12);

    String[] args13 = {"nodes_small.csv", "edges_small.csv", "output.csv",
        "--processing-flag", "s", "--number-of-users-to-process", "30",
        "--number-of-recommendations", "10"};
    cmdHandler13 = new CmdHandler(args13);

    String[] args14 = {"nodes_small.csv", "edges_small.csv", "output.csv",
        "--processing-flag", "e", "--number-of-users-to-process", "0"};
    cmdHandler14 = new CmdHandler(args14);
  }

  @Test
  public void getNodeFile() throws Exception {
    assertTrue(cmdHandler1.getNodeFile().equals("nodes_small.csv"));
    assertTrue(cmdHandler3.getNodeFile().equals("nodes_10000.csv"));
  }

  @Test
  public void getEdgeFile() throws Exception {
    assertTrue(cmdHandler1.getEdgeFile().equals("edges_small.csv"));
    assertTrue(cmdHandler3.getEdgeFile().equals("edges_10000.csv"));
  }

  @Test
  public void getOutputFile() throws Exception {
    assertTrue(cmdHandler1.getOutputFile().equals("output.csv"));
    assertTrue(cmdHandler3.getOutputFile().equals("output.csv"));
  }

  @Test
  public void getProcessingFlag() throws Exception {
    assertTrue(cmdHandler1.getProcessingFlag() == 's');
    assertTrue(cmdHandler3.getProcessingFlag() == 'r');
  }

  @Test
  public void getNumUsersToProcess() throws Exception {
    assertTrue(cmdHandler1.getNumUsersToProcess() == 100);
    assertTrue(cmdHandler3.getNumUsersToProcess() == 30);
  }

  @Test
  public void getNumRecommendations() throws Exception {
    assertTrue(cmdHandler1.getNumRecommendations() == 15);
    assertTrue(cmdHandler3.getNumRecommendations() == 10);
  }

  @Test
  public void isValid() throws Exception {
    assertTrue(cmdHandler1.isValid());
    assertTrue(!cmdHandler2.isValid());
    assertTrue(cmdHandler3.isValid());
    assertTrue(!cmdHandler4.isValid());
    assertTrue(!cmdHandler5.isValid());
    assertTrue(!cmdHandler6.isValid());
    assertTrue(!cmdHandler7.isValid());
    assertTrue(!cmdHandler8.isValid());
    assertTrue(!cmdHandler9.isValid());
    assertTrue(!cmdHandler10.isValid());
    assertTrue(!cmdHandler11.isValid());
    assertTrue(!cmdHandler12.isValid());
    assertTrue(cmdHandler13.isValid());
    assertTrue(!cmdHandler14.isValid());
  }

  @Test
  public void getErrorMessage() throws Exception {
    assertTrue(cmdHandler1.getErrorMessage().equals(""));
    assertTrue(!cmdHandler2.getErrorMessage().equals(""));
    assertTrue(cmdHandler3.getErrorMessage().equals(""));
    assertTrue(!cmdHandler4.getErrorMessage().equals(""));
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!cmdHandler1.equals(null));
    assertTrue(cmdHandler1.equals(cmdHandler1));
    assertTrue(!cmdHandler1.equals(""));
    assertTrue(!cmdHandler1.equals(cmdHandler2));
    assertTrue(!cmdHandler1.equals(cmdHandler3));
    assertTrue(!cmdHandler1.equals(cmdHandler4));
    assertTrue(!cmdHandler1.equals(cmdHandler13));
    assertTrue(cmdHandler1.equals(new CmdHandler(
        new String[] {"nodes_small.csv", "edges_small.csv", "output.csv"})));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(cmdHandler1.hashCode() == cmdHandler1.hashCode());
  }

}