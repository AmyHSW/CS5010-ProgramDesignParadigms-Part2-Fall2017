package edu.neu.ccs.cs5010.assignment8.query;

/**
 * The IQueryGenerator interface represents a query generator.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IQueryGenerator {

  /**
   * Returns true if there is next query to generate.
   * @return true if there is next query to generate; false otherwise.
   */
  boolean hasNextQuery();

  /**
   * Returns next query.
   * @return next query.
   */
  IQuery nextQuery();
}
