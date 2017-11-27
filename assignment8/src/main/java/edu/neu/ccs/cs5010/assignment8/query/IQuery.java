package edu.neu.ccs.cs5010.assignment8.query;

public interface IQuery {

  int getQueryId();

  int getParameter();

  static IQuery createQuery(int queryId, int parameter) {
    return new Query(queryId, parameter);
  }
}
