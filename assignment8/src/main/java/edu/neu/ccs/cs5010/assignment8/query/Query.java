package edu.neu.ccs.cs5010.assignment8.query;

/**
 * The Query class represents a query.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class Query implements IQuery {

  private final int queryId;
  private final int parameter;

  /**
   * Constructs a new query with the query id and parameter.
   * @param queryId the query id
   * @param parameter the parameter
   */
  public Query(int queryId, int parameter) {
    this.queryId = queryId;
    this.parameter = parameter;
  }

  /**
   * Returns the query id of this query.
   * @return the query id.
   */
  @Override
  public int getQueryId() {
    return queryId;
  }

  /**
   * Returns the parameter of this query.
   * @return the parameter.
   */
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

    if (queryId != query.queryId) {
      return false;
    }
    return parameter == query.parameter;
  }

  @Override
  public int hashCode() {
    int result = queryId;
    result = 31 * result + parameter;
    return result;
  }
}
