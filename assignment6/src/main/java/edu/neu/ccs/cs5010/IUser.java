package edu.neu.ccs.cs5010;

import java.time.LocalDate;

public interface IUser extends Comparable<IUser> {

  static IUser createUser(int userId, LocalDate createdDate, char gender, int age, String city) {
    return new User(userId, createdDate, gender, age, city);
  };

  int getAge();

  String getCity();

  int getUserId();

  void addOneFollower();

  int getNumFollowers();

  LocalDate getCreatedDate();

}
