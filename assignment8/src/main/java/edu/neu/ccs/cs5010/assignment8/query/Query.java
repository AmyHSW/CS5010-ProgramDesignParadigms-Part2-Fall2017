package edu.neu.ccs.cs5010.assignment8.query;

public class Query implements IQuery {

  private final int queryId;
  private final int parameter;

  public Query(int queryId, int parameter) {
    this.queryId = queryId;
    this.parameter = parameter;
  }

  @Override
  public int getQueryId() {
    return queryId;
  }

  @Override
  public int getParameter() {
    return parameter;
  }
}
