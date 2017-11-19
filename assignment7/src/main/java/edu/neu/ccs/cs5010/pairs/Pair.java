package edu.neu.ccs.cs5010.pairs;

public class Pair implements IPair {

  private final String first;
  private final String last;

  public Pair(String first, String last) {
    this.first = first;
    this.last = last;
  }

  @Override
  public String getFirst() {
    return first;
  }

  @Override
  public String getLast() {
    return last;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;

    Pair pair = (Pair) other;

    if (!first.equals(pair.first)) return false;
    return last.equals(pair.last);
  }

  @Override
  public int hashCode() {
    int result = first != null ? first.hashCode() : 0;
    result = 31 * result + (last != null ? last.hashCode() : 0);
    return result;
  }
}
