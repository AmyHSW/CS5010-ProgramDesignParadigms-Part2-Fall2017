package edu.neu.ccs.cs5010;

public class Edge implements IEdge {

  private final int fromId;
  private final int toId;

  public Edge(int fromId, int toId) {
    this.fromId = fromId;
    this.toId = toId;
  }

  @Override
  public int getFromId() {
    return fromId;
  }

  @Override
  public int getToId() {
    return toId;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Edge edge = (Edge) other;

    if (fromId != edge.fromId) {
      return false;
    }
    return toId == edge.toId;
  }

  @Override
  public int hashCode() {
    int result = fromId;
    result = 31 * result + toId;
    return result;
  }
}
