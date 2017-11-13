package edu.neu.ccs.cs5010;

/**
 * Represent a Edge interface.
 */
public interface IEdge {
  /**
   * Static factory method to create an User object with the given parameters.
   *
   * @param fromId the edge's source user's id
   * @param toId the edge's destination user's id
   */
  static IEdge createEdge(int fromId, int toId) {
    return new Edge(fromId, toId);
  }

  /**
   * Gets the edge's source user's id.
   *
   * @return int the edge's source user's id
   */
  int getFromId();

  /**
   * Gets the edge's destination user's id.
   *
   * @return int the edge's destination user's id
   */
  int getToId();
}
