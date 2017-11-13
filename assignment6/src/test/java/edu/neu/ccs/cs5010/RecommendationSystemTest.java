package edu.neu.ccs.cs5010;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecommendationSystemTest {

  private String[] args1 = {"nodes_small.csv", "edges_small.csv", "output1.csv"};
  private String[] args2 = {"nodes_small.csv", "edges_small.csv", "output2.csv",
                            "--processing-flag", "e"};
  private String[] args3 = {"nodes_small.csv", "edges_small.csv", "output3.csv",
                            "--processing-flag", "r", "--num-of-recommendations", "5"};
  private String[] args4 = {"nodes_small.csv", "edges_small.csv", "output4.csv"};
  private String[] args5 = {"nodes_10000.csv", "edges_10000.csv", "output5.csv"};

  private IRecommendationSystem recommendationSystem1 =
      new RecommendationSystem(new CmdHandler(args1));
  private IRecommendationSystem recommendationSystem2 =
      new RecommendationSystem(new CmdHandler(args2));
  private IRecommendationSystem recommendationSystem3 =
      new RecommendationSystem(new CmdHandler(args3));
  private IRecommendationSystem recommendationSystem4 =
      new RecommendationSystem(new CmdHandler(args4));
  private IRecommendationSystem recommendationSystem5 =
      new RecommendationSystem(new CmdHandler(args5));
  private IRecommendationSystem recommendationSystem1_copy =
      new RecommendationSystem(new CmdHandler(args1));


  @Test
  public void testRecommendations() throws Exception {
    recommendationSystem1.startRecommendation();
    recommendationSystem2.startRecommendation();
    recommendationSystem3.startRecommendation();
    recommendationSystem4.startRecommendation();
    recommendationSystem5.startRecommendation();
    recommendationSystem1_copy.startRecommendation();

    recommendationSystem1.outputResults();
    recommendationSystem2.outputResults();
    recommendationSystem3.outputResults();
    recommendationSystem4.outputResults();
    recommendationSystem5.outputResults();
    recommendationSystem1_copy.outputResults();

    recommendationSystem1.printTopTen();
    recommendationSystem2.printTopTen();
    recommendationSystem3.printTopTen();
    recommendationSystem4.printTopTen();
    recommendationSystem5.printTopTen();
    recommendationSystem1_copy.printTopTen();
  }

  @Test
  public void main() throws Exception {
    RecommendationSystem.main(args1);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!recommendationSystem1.equals(null));
    assertTrue(recommendationSystem1.equals(recommendationSystem1));
    assertTrue(!recommendationSystem1.equals(""));
    assertTrue(!recommendationSystem1.equals(recommendationSystem3));
    assertTrue(!recommendationSystem1.equals(recommendationSystem5));
    assertTrue(!recommendationSystem1.equals(recommendationSystem2));
    assertTrue(!recommendationSystem1.equals(recommendationSystem4));
    assertTrue(recommendationSystem1.equals(recommendationSystem1_copy));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(recommendationSystem1.hashCode() == recommendationSystem1_copy.hashCode());
  }

  @Test(expected = InvalidInputException.class)
  public void expectedInvalidInputException() throws Exception {
    RecommendationSystem.main(new String[] {"nodes_small.csv"});
  }

}