package edu.neu.ccs.cs5010;

import java.time.LocalDate;

public class User implements IUser {

  private static final char FEMALE = 'F';
  private static final char MALE = 'M';
  private static final char GENDER_OTHER = 'O';
  private static final int AGE_LOWER_BOUND = 0;
  private static final int AGE_UPPER_BOUND = 100;

  private int userId;
  private LocalDate createdDate;
  private char gender;
  private int age;
  private String city;
  private int numFollowers;

  public User(int userId, LocalDate createdDate, char gender, int age, String city) {
    validateGender(gender);
    validateAge(age);

    this.userId = userId;
    this.createdDate = createdDate;
    this.gender = gender;
    this.age = age;
    this.city = city;
  }

  private void validateGender(char gender) {
    if (gender != FEMALE && gender != MALE && gender != GENDER_OTHER) {
      throw new InvalidUserGenderException(gender + " is not valid");
    }
  }

  private void validateAge(int age) {
    if (age < AGE_LOWER_BOUND || age > AGE_UPPER_BOUND) {
      throw new InvalidUserAgeException(age + " is not between "
      + AGE_LOWER_BOUND + " and " + AGE_UPPER_BOUND);
    }
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
    if (that == null) {
      throw new InvalidInputException("Should provide IUser for compareTo, not null");
    }
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
