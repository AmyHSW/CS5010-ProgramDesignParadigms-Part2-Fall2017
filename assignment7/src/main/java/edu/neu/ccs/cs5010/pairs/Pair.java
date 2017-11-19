package edu.neu.ccs.cs5010.pairs;

public class Pair implements IPair {

  private final String first;
  private final String last;

  public Pair(String first, String last) {
    validate(first, last);
    this.first = first;
    this.last = last;
  }

  private void validate(String str1, String str2) {
    if (str1 == null || str2 == null) {
      throw new IllegalArgumentException("One or more of the strings in pair is null.");
    }
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
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Pair pair = (Pair) other;

    if (!first.equals(pair.first)) {
      return false;
    }
    return last.equals(pair.last);
  }

  @Override
  public int hashCode() {
    int result = first.hashCode();
    result = 31 * result + last.hashCode();
    return result;
  }
}
