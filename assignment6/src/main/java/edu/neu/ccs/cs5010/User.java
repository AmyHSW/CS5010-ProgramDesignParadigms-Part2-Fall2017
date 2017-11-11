package edu.neu.ccs.cs5010;

import java.time.LocalDate;

public class User implements IUser {
  private int userId;
  private LocalDate createdDate;
  private char gender;
  private int age;
  private String city;
  private int numFollowers;

  public User(int userId, LocalDate createdDate, char gender, int age, String city) {
    this.userId = userId;
    this.createdDate = createdDate;
    this.gender = gender;
    this.age = age;
    this.city = city;
  }

  @Override
  public int getUserId() {
    return userId;
  }

  @Override
  public void addOneFollower() {
    numFollowers++;
  }

  @Override
  public int getNumFollowers() {
    return numFollowers;
  }

  @Override
  public LocalDate getCreatedDate() {
    return createdDate;
  }

  @Override
  public int compareTo(IUser that) {
    return this.getUserId() - that.getUserId();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    User user = (User) other;

    return userId == user.userId;
  }

  @Override
  public int getAge() {
    return age;
  }

  @Override
  public String getCity() {
    return city;
  }

  @Override
  public int hashCode() {
    return userId;
  }

  @Override
  public String toString() {
    return "User{" + "userId=" + userId + ", createdDate=" + createdDate + ", gender=" + gender
        + ", age=" + age + ", city=" + city + ", numFollowers=" + numFollowers + "}";
  }
}
