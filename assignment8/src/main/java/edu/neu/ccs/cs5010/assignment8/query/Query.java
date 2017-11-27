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

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Query query = (Query) other;

    if (queryId != query.queryId) return false;
    return parameter == query.parameter;
  }

  @Override
  public int hashCode() {
    int result = queryId;
    result = 31 * result + parameter;
    return result;
  }
}
