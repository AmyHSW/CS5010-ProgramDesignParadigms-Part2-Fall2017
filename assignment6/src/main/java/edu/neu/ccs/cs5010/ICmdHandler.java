package edu.neu.ccs.cs5010;

public interface ICmdHandler {

  String getNodeFile();

  String getEdgeFile();

  String getOutputFile();

  char getProcessingFlag();

  int getNumUsersToProcess();

  int getNumRecommendations();

  int getInfluencerBound();

  boolean isValid();

  String getErrorMessage();
}
