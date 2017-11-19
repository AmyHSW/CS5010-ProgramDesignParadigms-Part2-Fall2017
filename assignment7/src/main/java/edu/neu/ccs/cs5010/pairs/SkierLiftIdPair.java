package edu.neu.ccs.cs5010.pairs;

public class SkierLiftIdPair implements Pair {

  private final String skierId;
  private final String liftId;

  public SkierLiftIdPair(String skierId, String liftId) {
    this.skierId = skierId;
    this.liftId = liftId;
  }

  @Override
  public String getFirst() {
    return skierId;
  }

  @Override
  public String getLast() {
    return liftId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SkierLiftIdPair that = (SkierLiftIdPair) o;

    if (skierId != null ? !skierId.equals(that.skierId) : that.skierId != null) return false;
    return liftId != null ? liftId.equals(that.liftId) : that.liftId == null;
  }

  @Override
  public int hashCode() {
    int result = skierId != null ? skierId.hashCode() : 0;
    result = 31 * result + (liftId != null ? liftId.hashCode() : 0);
    return result;
  }
}
