package edu.neu.ccs.cs5010.assignment8.query;

/**
 * The IQuery interface represents a query.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IQuery {

  /**
   * Returns the query id of this query.
   * @return the query id.
   */
  int getQueryId();

  /**
   * Returns the parameter of this query.
   * @return the parameter.
   */
  int getParameter();

  /**
   * Creates a new query with the specified query id and parameter.
   * @param queryId the query id
   * @param parameter the parameter
   * @return the new query
   */
  static IQuery createQuery(int queryId, int parameter) {
    return new Query(queryId, parameter);
  }
}
