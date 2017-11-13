package edu.neu.ccs.cs5010;

import org.junit.Test;

public class RecommendationSystemBonusTest {

  private String[] args = {"nodes_small.csv", "edges_small.csv", "output7.csv"};
  private IRecommendationSystem recommendationSystem =
      new RecommendationSystemBonus(new CmdHandler(args));

  @Test
  public void testRecommendation() throws Exception {
    recommendationSystem.startRecommendation();
    recommendationSystem.outputResults();
    recommendationSystem.printTopTen();
  }

  @Test
  public void main() throws Exception {
    RecommendationSystemBonus.main(args);
  }


  @Test(expected = InvalidInputException.class)
  public void expectedInvalidInputException() throws Exception {
    RecommendationSystemBonus.main(new String[] {"nodes.csv"});
  }
}