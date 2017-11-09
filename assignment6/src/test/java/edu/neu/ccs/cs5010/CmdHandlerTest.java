package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CmdHandlerTest {

  private ICmdHandler cmdHandler;

  @Before
  public void setUp() throws Exception {
    String[] args = {"nodes_small.csv", "edges_small_csv", "output.csv"};
    cmdHandler = new CmdHandler(args);
    
  }

  @Test
  public void getNodeFile() throws Exception {
  }

  @Test
  public void getEdgeFile() throws Exception {
  }

  @Test
  public void getOutputFile() throws Exception {
  }

  @Test
  public void getProcessingFlag() throws Exception {
  }

  @Test
  public void getNumUsersToProcess() throws Exception {
  }

  @Test
  public void getNumRecommendations() throws Exception {
  }

  @Test
  public void isValid() throws Exception {
  }

  @Test
  public void getErrorMessage() throws Exception {
  }

  @Test
  public void equals() throws Exception {
  }

  @Test
  public void testHashCode() throws Exception {
  }

}