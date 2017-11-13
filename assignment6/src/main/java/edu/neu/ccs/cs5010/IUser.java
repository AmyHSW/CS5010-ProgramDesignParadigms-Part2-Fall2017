package edu.neu.ccs.cs5010;

import java.time.LocalDate;

/**
 * Represent a User interface.
 */
public interface IUser extends Comparable<IUser> {
  /**
   * Static factory method to create an User object with the given parameters.
   *
   * @param userId the user's id number
   * @param createdDate the user account's created date
   * @param gender the user's gender,
   * @param age the user's age,
   * @param city the user's city
   * @throws InvalidUserGenderException if gender is not a character from M/F/O
   * @throws InvalidUserAgeException if age is not an integer between 0 to 100
   */
  static IUser createUser(int userId, LocalDate createdDate, char gender, int age, String city) {
    return new User(userId, createdDate, gender, age, city);
  }

  /**
   * Gets the user's age.
   *
   * @return int the user's age.
   */
  int getAge();

  /**
   * Gets the user's city.
   *
   * @return int the user's city.
   */
  String getCity();

  /**
   * Gets the user's unique id.
   *
   * @return int the user's id.
   */
  int getUserId();

  /**
   * When one new user following this user, adds one follower for this user.
   */
  void addOneFollower();

  /**
   * Gets the user's number of followers.
   *
   * @return int the user's number of followers.
   */
  int getNumFollowers();

  /**
   * Gets the user's created account date.
   *
   * @return LocalDate the user's created date.
   */
  LocalDate getCreatedDate();

}
