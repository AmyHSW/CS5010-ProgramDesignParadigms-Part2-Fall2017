package edu.neu.ccs.cs5010.skier;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkierTest {
  private ISkier iSkier1;
  private ISkier iSkier2;
  private ISkier iSkier3;

  @Before
  public void setUp() throws Exception {
    iSkier1 = new Skier("123");
    iSkier2 = new Skier("123");
    iSkier3 = new Skier("234");
  }

  @Test
  public void incrementNumRides() throws Exception {
    iSkier1.incrementNumRides();
    iSkier1.incrementNumRides();
    assertTrue(iSkier1.getNumRides() == 2);
  }

  @Test
  public void incrementVerticalMeters() throws Exception {
    iSkier2.increaseVerticalMeters(200);
    iSkier2.increaseVerticalMeters(300);
    assertTrue(iSkier2.getVerticalMeters() == 500);
  }

  @Test
  public void compareTo() throws Exception {
    iSkier1.increaseVerticalMeters(200);
    iSkier3.increaseVerticalMeters(300);
    assertTrue(iSkier1.compareTo(iSkier3) > 0);
  }

  @Test(expected = NullPointerException.class)
  public void expectedNullPointerException() throws Exception {
    iSkier1.compareTo(null);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!iSkier1.equals(null));
    assertTrue(iSkier1.equals(iSkier1));
    assertTrue(iSkier1.equals(iSkier2));
    assertTrue(!iSkier1.equals(iSkier3));
    assertTrue(!iSkier1.equals(""));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(iSkier1.hashCode() == iSkier2.hashCode());
    assertTrue(iSkier1.hashCode() != iSkier3.hashCode());
  }
}