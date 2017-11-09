package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserTest {
  private IUser user1;
  private IUser user2;
  private IUser user3;

  @Before
  public void setUp() throws Exception {
    LocalDate today = LocalDate.now();
    user1 = new User(1, today, 'F', 20, "Seattle");
    user2 = new User(1, today, 'F', 20, "Seattle");
    user3 = new User(2, today, 'F', 24, "Seattle");
  }

  @Test
  public void testEquals() throws Exception {
    assertTrue("User failed to provide the correct equal.",
            user1.equals(user1));
    assertFalse("User failed to provide the correct equal.",
            user1.equals(null));
    assertFalse("User failed to provide the correct equal.",
            user1.equals("1"));
    assertTrue("User failed to provide the correct equal.",
            user1.equals(user2));
    assertFalse("User failed to provide the correct equal.",
            user1.equals(user3));
    assertTrue("User failed to provide the correct hashcode.",
            user1.hashCode() == user2.hashCode());
    assertFalse("User failed to provide the correct hashcode.",
            user1.hashCode() == user3.hashCode());
  }

  @Test
  public void testToString() throws Exception {
    user1.toString();
  }
}