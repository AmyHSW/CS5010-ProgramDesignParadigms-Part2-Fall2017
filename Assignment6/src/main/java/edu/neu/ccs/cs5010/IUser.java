package edu.neu.ccs.cs5010;

import java.time.LocalDate;

public interface IUser extends Comparable<IUser> {
  int getUserId();
  void addOneFollower();
  void addOneFollowee();
  int getNumFollowees();
  int getNumFollowers();
  LocalDate getCreatedDate();

}
