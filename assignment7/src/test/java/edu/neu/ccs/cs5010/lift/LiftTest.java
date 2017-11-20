package edu.neu.ccs.cs5010.lift;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LiftTest {
  private ILift lift1;
  private ILift lift2;
  private ILift lift3;

  @Before
  public void setUp() throws Exception {
    lift1 = new Lift(2);
    lift2 = new Lift(2);
    lift3 = new Lift(5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException() throws Exception {
    new Lift(40);
  }

  @Test
  public void incrementNumber() throws Exception {
    lift1.incrementNumber();
    lift1.incrementNumber();
    assertTrue(lift1.getNumber() == 2);
  }

  @Test(expected = NullPointerException.class)
  public void expectedNullPointerException() throws Exception {
    lift1.compareTo(null);
  }
  @Test
  public void compareTo() throws Exception {
    lift1.incrementNumber();
    lift1.incrementNumber();
    assertTrue(lift1.compareTo(lift3) < 0);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!lift1.equals(null));
    assertTrue(lift1.equals(lift1));
    assertTrue(lift1.equals(lift2));
    assertTrue(!lift1.equals(lift3));
    assertTrue(!lift1.equals(""));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(lift1.hashCode() == lift2.hashCode());
    assertTrue(lift1.hashCode() != lift3.hashCode());
  }
}