package edu.neu.ccs.cs5010;

import java.util.NoSuchElementException;

/**
 * Represent a user generator interface.
 */
public interface IUserGenerator {
  /**
   * Checks if is able to generate next user.
   *
   * @return true if there's next user to generate.
   */
  boolean hasNextUser();

  /**
   * Gets the next User.
   *
   * @return the next User
   * @throws NoSuchElementException if there are no more users to generate
   */
  IUser getNextUser();
}
