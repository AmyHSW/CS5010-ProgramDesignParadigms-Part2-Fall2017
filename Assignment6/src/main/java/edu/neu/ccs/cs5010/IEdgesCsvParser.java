package edu.neu.ccs.cs5010;

import edu.neu.ccs.cs5010.IEdge;

public interface IEdgesCsvParser {

  IEdge getNextEdge();

  boolean hasNextEdge();
}
