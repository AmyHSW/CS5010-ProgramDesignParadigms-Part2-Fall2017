package edu.neu.ccs.cs5010;

import java.util.NoSuchElementException;

/**
 * Represent an edge generator interface.
 */
public interface IEdgeGenerator {
  /**
   * Checks if is able to generate next edge.
   *
   * @return true if there's next edge to generate.
   */
  boolean hasNextEdge();

  /**
   * Gets the next Edge.
   *
   * @return the next Edge
   * @throws NoSuchElementException if there are no more edges to generate
   */
  IEdge getNextEdge();
}
