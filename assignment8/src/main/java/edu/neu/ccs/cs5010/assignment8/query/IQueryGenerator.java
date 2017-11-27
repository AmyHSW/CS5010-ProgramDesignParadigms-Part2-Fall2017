package edu.neu.ccs.cs5010.assignment8.query;

public interface IQueryGenerator {

  boolean hasNextQuery();

  IQuery nextQuery();
}
