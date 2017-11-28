package edu.neu.ccs.cs5010.assignment8.cmdhandler;

/**
 * The IQueryCmdHandler interface represents a cmd handler.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IQueryCmdHandler extends ICmdHandler {
  /**
   * Returns the number of queries.
   * @return the number of queries.
   */
  int getNumQueries();


}
