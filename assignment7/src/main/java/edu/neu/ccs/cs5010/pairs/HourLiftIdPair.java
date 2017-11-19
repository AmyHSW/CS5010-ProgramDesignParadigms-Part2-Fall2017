package edu.neu.ccs.cs5010.pairs;

public class HourLiftIdPair implements Pair {

  private final String hour;
  private final String liftId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    HourLiftIdPair that = (HourLiftIdPair) o;

    if (hour != null ? !hour.equals(that.hour) : that.hour != null) return false;
    return liftId != null ? liftId.equals(that.liftId) : that.liftId == null;
  }

  @Override
  public int hashCode() {
    int result = hour != null ? hour.hashCode() : 0;
    result = 31 * result + (liftId != null ? liftId.hashCode() : 0);
    return result;
  }

  public HourLiftIdPair(String hour, String liftId) {
    this.hour = hour;
    this.liftId = liftId;
  }

  @Override
  public String getFirst() {
    return hour;
  }

  @Override
  public String getLast() {
    return liftId;
  }
}
