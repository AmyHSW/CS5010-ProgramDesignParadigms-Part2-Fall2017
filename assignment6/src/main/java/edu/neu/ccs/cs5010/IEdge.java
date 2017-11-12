package edu.neu.ccs.cs5010;

public interface IEdge {

  static IEdge createEdge(int fromId, int toId) {
    return new Edge(fromId, toId);
  };

  int getFromId();

  int getToId();
}
