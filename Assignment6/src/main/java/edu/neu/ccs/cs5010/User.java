package edu.neu.ccs.cs5010;

import java.time.LocalDate;

public class User implements IUser {

  public User(int userId, LocalDate date, Gender gender, int age, String city) {}

  @Override
  public int getUserId() {
    return 0;
  }

  @Override
  public void addOneFollower() {

  }

  @Override
  public void addOneFollowee() {

  }

  @Override
  public int getNumFollowees() {
    return 0;
  }

  @Override
  public int getNumFollowers() {
    return 0;
  }

  @Override
  public LocalDate getCreatedDate() {
    return null;
  }

  @Override
  public int compareTo(IUser that) {
    return this.getUserId() - that.getUserId();
  }
}
